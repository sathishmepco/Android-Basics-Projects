package com.cdac.asynctaskdemo;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button buttonDownloadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        imageView = findViewById(R.id.imageView);
        buttonDownloadImage = findViewById(R.id.buttonDownloadImage);

        buttonDownloadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sUrl = "https://cdn-images-1.medium.com/max/1600/1*Pyo8pnwcNoOt1_yO1OrOcw.jpeg";
                String params[] = {sUrl};
                new ImageDownloaderTask().execute(params);
            }
        });
    }

    class ImageDownloaderTask extends AsyncTask<String,Void, Bitmap>{

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Download Image");
            progressDialog.setMessage("please wait ...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            System.out.println("Url is "+strings[0]);
            return downloadImage(strings[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
            progressDialog.dismiss();
        }
    }

    private Bitmap downloadImage(String sUrl){
        URL url = null;
        try {
            url = new URL(sUrl);
            InputStream is = url.openStream();
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}