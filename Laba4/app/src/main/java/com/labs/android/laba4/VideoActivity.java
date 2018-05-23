package com.labs.android.laba4;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    VideoView videoPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
    }

    public void playInternetVideo(View view){
        videoPlayer =  (VideoView)findViewById(R.id.videoPlayer);
        videoPlayer.setVideoPath("http://video.ch9.ms/ch9/507d/71f4ef0f-3b81-4d2c-956f-c56c81f9507d/AndroidEmulatorWithMacEmulator.mp4");
        MediaController mediaController = new MediaController(this);
        videoPlayer.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoPlayer);
    }

    public void playPhoneVideo(View view){
        videoPlayer =  (VideoView)findViewById(R.id.videoPlayer);
        Uri myVideoUri= Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.v1);
        videoPlayer.setVideoURI(myVideoUri);
        MediaController mediaController = new MediaController(this);
        videoPlayer.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoPlayer);
    }
    public void play(View view){
        videoPlayer.start();
    }
    public void pause(View view){
        videoPlayer.pause();
    }
    public void stop(View view){
        videoPlayer.stopPlayback();
        videoPlayer.resume();
    }
}
