package com.cdac.broadcastreceiverdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class PowerBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
            String action = Intent.ACTION_POWER_CONNECTED;
            showNotification(context,action,1);
        }
        else if(intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
            String action = Intent.ACTION_POWER_DISCONNECTED;
            showNotification(context,action,2);
        }
    }

    private void showNotification(Context context,String action,int notificationId){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context,"mychannel");
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("Broadcast Receiver");
        mBuilder.setContentText(action.substring(action.lastIndexOf(".")+1));
        mBuilder.setAutoCancel(true);
        Intent resultIntent = new Intent(context, PowerActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        resultIntent.putExtra("action",action);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context,notificationId,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        notificationManager.notify(notificationId, mBuilder.build());
    }
}