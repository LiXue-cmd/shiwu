package com.example.shiwu.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.shiwu.R;

public class shipin extends AppCompatActivity {

    Button clk;
    VideoView videov;
    MediaController mediaC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_shipin);

        clk = (Button) findViewById(R.id.button);
        videov = (VideoView) findViewById(R.id.videoView);
        mediaC = new MediaController(this);
        String video_path = "android.resource://com.example.shiwu/" + R.raw.laji;
        Uri uri = Uri.parse(video_path);
        videov.setVideoURI(uri);
        //videov.setMediaController(mediaC);
        //mediaC.setAnchorView(videov);
        videov.start();

    }

    public void videoPlay(View view) {

        String video_path = "android.resource://com.example.shiwu/" + R.raw.laji;
        Uri uri = Uri.parse(video_path);
        videov.setVideoURI(uri);
        videov.setMediaController(mediaC);
        mediaC.setAnchorView(videov);
        videov.start();

    }
}