package com.greenfox.lica.starfox;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HighScore extends AppCompatActivity {

    TextView textView,textView2,textView3,textView4;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);

        sharedPreferences  = getSharedPreferences("SHAR_PREF_NAME", Context.MODE_PRIVATE);

        textView.setText("First place: " + sharedPreferences.getInt("score1",0));
        textView2.setText("Second place " + sharedPreferences.getInt("score2",0));
        textView3.setText("Third place " + sharedPreferences.getInt("score3",0));
        textView4.setText("Fourth place " + sharedPreferences.getInt("score4",0));
    }
}
