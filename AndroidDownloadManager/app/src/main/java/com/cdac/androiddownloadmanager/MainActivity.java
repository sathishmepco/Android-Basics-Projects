package com.cdac.androiddownloadmanager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.cdac.androiddownloadmanager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.contentView.setEvent(this);
        setSupportActionBar(binding.toolbar);
        permissionCheck();
    }

    public void downloadImage(){
        Toast.makeText(this, "image button clicked", Toast.LENGTH_SHORT).show();
        String downloadPath = "https://user-images.githubusercontent.com/5689784/63573563-4507f000-c5a3-11e9-99b0-5a1b9bddb96d.jpg";
        //Download manager stores in external storage so don't need to specify the full path
        //Just need to specify the directory name
        String destDirPath = "Download Manager";
        DownloadService.startDownloadService(getApplicationContext(),downloadPath,destDirPath);
    }

    public void downloadAudioFile(){
        Toast.makeText(this, "audio button clicked", Toast.LENGTH_SHORT).show();
        String downloadPath = "https://file-examples.com/wp-content/uploads/2017/11/file_example_MP3_5MG.mp3";
        String destDirPath = "Download Manager";
        DownloadService.startDownloadService(getApplicationContext(),downloadPath,destDirPath);
    }

    private void permissionCheck(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},
                    123);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 123){
            for(int i=0;i<grantResults.length;i++)
                if(grantResults[i] == PackageManager.PERMISSION_DENIED)
                    finish();
        }
    }
}