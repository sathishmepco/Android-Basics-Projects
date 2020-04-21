package com.codificador.photowithexifdata;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_PERMISSION = 2;
    String currentPhotoPath = null;

    ImageView imageView;
    LocationUtil locationUtil;
    static final String TAG = "EXIF_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isGPSEnabled())
                    launchCameraIntent();
                else
                    showGPSAlert();
            }
        });
        initPermission();
        imageView = findViewById(R.id.imageView);
    }

    private void initPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSION);
            return;
        }else{
            locationUtil = new LocationUtil(getApplicationContext());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults == null)
            finish();
        for(int result : grantResults)
            if(result == PackageManager.PERMISSION_DENIED)
                finish();
        locationUtil = new LocationUtil(getApplicationContext());
    }

    private void launchCameraIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
            try {
                storeImage(imageBitmap);
                addExifData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        //File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File storageDir = Environment.getExternalStorageDirectory();
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void storeImage(Bitmap image) throws IOException {
        File pictureFile = createImageFile();
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Error accessing file: " + e.getMessage());
        }
    }

    private boolean isGPSEnabled(){
        LocationManager manager = (LocationManager) getSystemService( LOCATION_SERVICE );
        return manager.isProviderEnabled( LocationManager.GPS_PROVIDER );
    }

    private void showGPSAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS needs to be enabled?")
                .setCancelable(false)
                .setPositiveButton("Enable", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void addExifData() throws IOException {
        File imageFile = new File(currentPhotoPath);
        String imagePath = imageFile.getCanonicalPath();
        ExifInterface exif = new ExifInterface(imagePath);
        Location currentLocation = locationUtil.getCurrentLocation();
        if(currentLocation == null)
            return;
        double lat = currentLocation.getLatitude();
        double alat = Math.abs(lat);
        String dms = Location.convert(alat, Location.FORMAT_SECONDS);
        String[] splits = dms.split(":");
        String[] secnds = (splits[2]).split("\\.");
        String seconds = (secnds.length==0) ? splits[2] : secnds[0];

        String latitudeStr = splits[0] + "/1," + splits[1] + "/1," + seconds + "/1";
        exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE, latitudeStr);
        exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF, lat>0?"N":"S");

        double lon = currentLocation.getLongitude();
        double alon = Math.abs(lon);
        dms = Location.convert(alon, Location.FORMAT_SECONDS);
        splits = dms.split(":");
        secnds = (splits[2]).split("\\.");

        seconds = (secnds.length==0) ? splits[2] : secnds[0];
        String longitudeStr = splits[0] + "/1," + splits[1] + "/1," + seconds + "/1";
        exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, longitudeStr);
        exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF, lon>0?"E":"W");
        exif.saveAttributes();
    }
}