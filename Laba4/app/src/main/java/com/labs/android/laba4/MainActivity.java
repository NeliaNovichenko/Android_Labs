package com.labs.android.laba4;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playMusic(View view) {
        Intent intent = new Intent(MainActivity.this, MusicActivity.class);
        startActivity(intent);
    }
    public void playVideo(View view) {
        Intent intent = new Intent(MainActivity.this, VideoActivity.class);
        startActivity(intent);
    }
}
