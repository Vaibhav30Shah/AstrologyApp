package com.royal.astrologyapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
public class SplashActivity extends AppCompatActivity {
    int time=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        GifImageViewActivity gifImageView = findViewById(R.id.img_gif);
        gifImageView.setGifImageResource(R.drawable.sign);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        },time);
    }
}