package com.greenfox.lica.starfox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton buttonPlay;
    private ImageButton buttonScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        buttonPlay = (ImageButton) findViewById(R.id.buttonPlay);
        buttonScore = (ImageButton) findViewById(R.id.buttonScore);
        buttonPlay.setOnClickListener(this);
        buttonPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonPlay) {
            startActivity(new Intent(MainActivity.this, GameActivity.class));
        }
        if (v == buttonScore) {
            startActivity(new Intent(MainActivity.this, HighScore.class));
        }
    }
}
