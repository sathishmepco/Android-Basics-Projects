package com.cdac.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MusicService extends Service {

    static final int PLAY = 1;
    static final int STOP = 2;
    static final int PAUSE = 3;
    static final int RESUME = 4;

    AudioPlayer audioPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        audioPlayer = new AudioPlayer(getApplicationContext());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int code = intent.getExtras().getInt("code");
        switch (code){
            case PLAY:
                audioPlayer.start();
                break;
            case STOP:
                audioPlayer.stop();
                stopSelf();
                break;
            case PAUSE:
                audioPlayer.pause();
                break;
            case RESUME:
                audioPlayer.resume();
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        audioPlayer.stop();
    }
}