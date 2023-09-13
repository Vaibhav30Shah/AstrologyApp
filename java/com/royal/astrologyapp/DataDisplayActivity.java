package com.royal.astrologyapp;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DataDisplayActivity extends AppCompatActivity {
    TextView TvData, tvUsername, tvtime, tvdate;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);
        tvUsername = findViewById(R.id.Tv_pData);
        tvtime = findViewById(R.id.tv_time);

        Intent i = getIntent();
        String strdata = i.getStringExtra("KEY_FN");
        String strdata1 = i.getStringExtra("KEY_LN");
        String strRating = i.getStringExtra("KEY_RATING");
        String day = i.getStringExtra("KEY_DATE");
        String month = i.getStringExtra("KEY_MONTH"); // Month is zero-based, so add 1
        String year = i.getStringExtra("KEY_YEAR");
        tvUsername.setText("UserName:  "+strdata+""+strdata1);
        tvtime.setText("Rating is: "+strRating);

    }
}
