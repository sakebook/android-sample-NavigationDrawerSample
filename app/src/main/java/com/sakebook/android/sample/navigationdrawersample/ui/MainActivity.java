package com.sakebook.android.sample.navigationdrawersample.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.sakebook.android.sample.navigationdrawersample.DrawerCallback;
import com.sakebook.android.sample.navigationdrawersample.adapter.NavigationDrawerListAdapter;
import com.sakebook.android.sample.navigationdrawersample.model.NavigationItem;
import com.sakebook.android.sample.navigationdrawersample.R;

import java.util.ArrayList;
import java.util.List;


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

        findViewById(R.id.button_movie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = GoogleMovieActivity.getIntent(MainActivity.this);
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
