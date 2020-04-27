package com.codificador.retrofitdemo;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Environment;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static int REQUEST_READ_WRITE_PERMISSION = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById(R.id.buttonGet).setOnClickListener(this);
        findViewById(R.id.buttonDownloadFile).setOnClickListener(this);
        findViewById(R.id.buttonGetJSON).setOnClickListener(this);
        findViewById(R.id.buttonPostJson).setOnClickListener(this);
        findViewById(R.id.buttonUploadFile).setOnClickListener(this);
        findViewById(R.id.buttonPostParams).setOnClickListener(this);
        initPermission();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonDownloadFile:
                downloadFile();
                break;
            case R.id.buttonGet:
                get();
                break;
            case R.id.buttonGetJSON:
                getJSON();
                break;
            case R.id.buttonPostJson:
                postJSON();
                break;
            case R.id.buttonPostParams:
                postParams();
                break;
            case R.id.buttonUploadFile:
                uploadFile();
                break;
        }
    }

    private void postJSON(){
        Contact contact = new Contact();
        contact.setName("Sathish Kumar");
        contact.setPhone("9988776655");
        contact.setEmail("sathishmepco@gmail.com");
        WebServices services = RetrofitConfig.getRetrofit().create(WebServices.class);
        services.addContact(contact).enqueue(new Callback<ContactResponse>() {
            @Override
            public void onResponse(Call<ContactResponse> call, Response<ContactResponse> response) {
                if(!response.isSuccessful()) {
                    try {
                        showAlertDialog("Failed",new String(response.errorBody().bytes(),"UTF-8"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    ContactResponse cr = response.body();
                    showAlertDialog("Success",cr.getMessage()+"   "+cr.isStatus());
                }
            }

            @Override
            public void onFailure(Call<ContactResponse> call, Throwable t) {
                showAlertDialog("Failed",t.getMessage());
            }
        });
    }

    private void uploadFile(){
        File storageDir = Environment.getExternalStorageDirectory();
        File file = new File(storageDir,"file_from_mobile.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write("Retrofit Demo for Android !".getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        RequestBody requestBody = RequestBody.create(file,MediaType.parse("*/*"));
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        WebServices services = RetrofitConfig.getRetrofit().create(WebServices.class);
        services.uploadFile(fileToUpload).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()) {
                    try {
                        showAlertDialog("Failed",new String(response.errorBody().bytes(),"UTF-8"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    showAlertDialog("Success",response.body());
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                showAlertDialog("Failed",t.getMessage());
            }
        });
    }

    private void downloadFile(){

    }

    private void getJSON(){
        WebServices services = RetrofitConfig.getRetrofit().create(WebServices.class);
        services.getContactList().enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                if(!response.isSuccessful()) {
                    try {
                        showAlertDialog("Failed",new String(response.errorBody().bytes(),"UTF-8"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    List<Contact> contacts = response.body();
                    showAlertDialog("Success",contacts.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                showAlertDialog("Failed",t.getMessage());
            }
        });
    }

    private void get(){

    }

    private void postParams(){
        String name = "Prem";
        String phone = "5544332211";
        String email = "prem@gmail.com";
        WebServices services = RetrofitConfig.getRetrofit().create(WebServices.class);
        services.postContact(name,phone,email).enqueue(new Callback<ContactResponse>() {
            @Override
            public void onResponse(Call<ContactResponse> call, Response<ContactResponse> response) {
                if(!response.isSuccessful()) {
                    try {
                        showAlertDialog("Failed",new String(response.errorBody().bytes(),"UTF-8"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    ContactResponse cr = response.body();
                    showAlertDialog("Success",cr.getMessage()+"   "+cr.isStatus());
                }
            }

            @Override
            public void onFailure(Call<ContactResponse> call, Throwable t) {
                showAlertDialog("Failed",t.getMessage());
            }
        });
    }

    private void showAlertDialog(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    private void initPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,},REQUEST_READ_WRITE_PERMISSION);
            return;
        }
    }
}