package com.cdac.androiddownloadmanager;

import android.app.DownloadManager;
import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import java.io.File;

import androidx.annotation.Nullable;

public class DownloadService extends IntentService {

    private static final String DOWNLOAD_PATH = "download_path";
    private static final String DESTINATION_PATH = "dest_path";
    private static final String MIME_TYPE = "mime";

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent,flags,startId);
    }

    public static void startDownloadService(Context context, String downloadPath, String destPath, String mimeType) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(DOWNLOAD_PATH, downloadPath);
        intent.putExtra(DESTINATION_PATH, destPath);
        intent.putExtra(MIME_TYPE,mimeType);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String downloadPath = intent.getStringExtra(DOWNLOAD_PATH);
            String destPath = intent.getStringExtra(DESTINATION_PATH);
            String mimeType = intent.getStringExtra(MIME_TYPE);
            startDownload(downloadPath,destPath,mimeType);
        }
    }

    private void startDownload(String downloadPath, String destPath,String mimeType){
        Uri uri = Uri.parse(downloadPath);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle("Downloading File");
        request.setDescription("Downloading the file...");
        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalPublicDir(destPath, uri.getLastPathSegment());
        SharedPreferences sharedPreferences = getSharedPreferences("pref",MODE_PRIVATE);
        sharedPreferences.edit().putString("path",destPath+ File.separator+uri.getLastPathSegment()).commit();
        sharedPreferences.edit().putString("mime",mimeType).commit();
        long refId = ((DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request);
        Log.d("TAG","download id is "+refId);
    }
}
