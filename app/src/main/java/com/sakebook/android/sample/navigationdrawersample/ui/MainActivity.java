package com.sakebook.android.sample.navigationdrawersample.ui;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

import com.sakebook.android.sample.navigationdrawersample.R;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity);
        initLayout();
    }


    private void initLayout() {
        findViewById(R.id.button_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = GooglePhotoActivity.getIntent(MainActivity.this);
                startActivity(intent);
            }
        });

        findViewById(R.id.button_music).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = GoogleMusicActivity.getIntent(MainActivity.this);
                startActivity(intent);
            }
        });

        findViewById(R.id.button_mail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = GoogleMailActivity.getIntent(MainActivity.this);
                startActivity(intent);
            }
        });

    }

}
