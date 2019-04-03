package com.cdac.servicedemo;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlayer {

    private MediaPlayer player;
    private Context context;
    private int curPos = -1;

    public AudioPlayer(Context context){
        this.context = context;
    }

    private void prepare(){
        player = MediaPlayer.create(context,R.raw.vaigai);
    }

    public void start(){
        if(player == null){
            prepare();
            player.start();
        }else{
            player.start();
        }
    }

    public void stop(){
        if(player != null)
            player.stop();
        player = null;
    }

    public void pause(){
        if(player != null && player.isPlaying()){
            player.pause();
            curPos = player.getCurrentPosition();
        }
    }

    public void resume(){
        if(player != null){
            player.seekTo(curPos);
            player.start();
            curPos = -1;
        }
    }
}