package com.royal.astrologyapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


public class KundliDisplay extends AppCompatActivity {

    ImageView imageView;
    TextView textView1, textView2, textView3, textView4, textView5, textView6,
    textView7, textView8, textView9, textView10, textView11, textView12, dobtv, nametv, birthtimetv;
    final String[] rashis = {"मेष", "वृषभ", "मिथुन", "कर्क", "सिंह", "कन्या", "तुला", "वृश्चिक", "धनु", "मकर", "कुंभ", "मीन"};
    String[] grah={"सूर्य","चंद्रमा","मंगल","बुध","बृहस्पति","शुक्र","शनि","राहु","केतु"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kundli_display);
        imageView = findViewById(R.id.imageview);
        textView1=findViewById(R.id.textView1);
        textView2=findViewById(R.id.textView2);
        textView3=findViewById(R.id.textView3);
        textView4=findViewById(R.id.textView4);
        textView5=findViewById(R.id.textView5);
        textView6=findViewById(R.id.textView6);
        textView7=findViewById(R.id.textView7);
        textView8=findViewById(R.id.textView8);
        textView9=findViewById(R.id.textView9);
        textView10=findViewById(R.id.textView10);
        textView11=findViewById(R.id.textView11);
        textView12=findViewById(R.id.textView12);
        nametv=findViewById(R.id.nametv);
        dobtv=findViewById(R.id.dobtv);
        birthtimetv=findViewById(R.id.birthtimetv);

        TextView[] textViews = {textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9,
            textView10, textView11, textView12};

        Intent itn = getIntent();
        String strname = itn.getStringExtra("KEY_FN");
        String strlnanme = itn.getStringExtra("KEY_LN");
        String strdate = itn.getStringExtra("KEY_DATE");
        String strtime = itn.getStringExtra("KEY_TIME");

        nametv.append(strname+" "+strlnanme);
        dobtv.append(strdate);
        birthtimetv.append(strtime);
        String[] rashi = generateRandomRashis();
        for (int i = 0; i < 12; i++) {
            textViews[i].append(rashi[i]);
        }
        for(int i=0; i<9; i++){
            textViews[i].append("\n"+grah[new Random().nextInt(grah.length)]);
        }

    }

    private String[] generateRandomRashis() {
        String[] allRashis = new String[12];
        System.arraycopy(rashis, 0, allRashis, 0, rashis.length);
        Collections.shuffle(Arrays.asList(allRashis));
        String[] rashiName = new String[12];
        int start = new Random().nextInt(12);
        for (int i = start; i < start + 12; i++) {
            rashis[i % 12] = allRashis[i - start];
        }

        return rashis;

    }
}

