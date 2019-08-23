package com.cdac.androiddownloadmanager;

import android.app.DownloadManager;
import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;

public class DownloadService extends IntentService {

    private static final String DOWNLOAD_PATH = "download_path";
    private static final String DESTINATION_PATH = "dest_path";

    public DownloadService() {
        super("DownloadService");
    }

    public static void startDownloadService(Context context, String downloadPath, String destPath) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(DOWNLOAD_PATH, downloadPath);
        intent.putExtra(DESTINATION_PATH, destPath);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String downloadPath = intent.getStringExtra(DOWNLOAD_PATH);
            String destPath = intent.getStringExtra(DESTINATION_PATH);
            startDownload(downloadPath,destPath);
        }
    }

    private void startDownload(String downloadPath, String destPath){
        Uri uri = Uri.parse(downloadPath);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle("Downloading...");
        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalPublicDir(destPath, uri.getLastPathSegment());
        ((DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request);
    }
}
