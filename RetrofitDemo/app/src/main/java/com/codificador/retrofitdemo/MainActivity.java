package com.codificador.retrofitdemo;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

/*        services.sendOTP(contact).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String strResponse = new String(response.body().bytes(),"UTF-8");
                    showAlertDialog("Success",strResponse);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showAlertDialog("Failed",t.getMessage());
            }
        });*/
    }

    private void uploadFile(){

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
//                    showAlertDialog("Success",contacts.size()+" number of contacts available!");
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
}