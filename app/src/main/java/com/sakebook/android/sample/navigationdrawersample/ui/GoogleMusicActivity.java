package com.sakebook.android.sample.navigationdrawersample.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.sakebook.android.sample.navigationdrawersample.DrawerCallback;
import com.sakebook.android.sample.navigationdrawersample.R;
import com.sakebook.android.sample.navigationdrawersample.adapter.NavigationDrawerListAdapter;
import com.sakebook.android.sample.navigationdrawersample.model.NavigationItem;

import java.util.ArrayList;
import java.util.List;

public class GoogleMusicActivity extends ActionBarActivity
        implements DrawerCallback {

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, GoogleMusicActivity.class);
        return intent;
    }


    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ListView mNavigationDrawer;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_music);

        initLayout();
        // tool bar設定
        settingToolbar();
        setUpHeaderView();
        setUpAdapter();
    }

    private void initLayout() {
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mNavigationDrawer = (ListView)findViewById(R.id.navigation_drawer);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mToolbar.setTitle("open");
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mToolbar.setTitle("close");
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.setStatusBarBackgroundColor(Color.parseColor("#ff6600"));
//        mDrawerLayout.setStatusBarBackground(R.drawable.ic_launcher);
    }

    private void settingToolbar() {
        //icon 表示
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();
    }

    private void setUpHeaderView() {
        ImageView header = new ImageView(this);
        header.setImageResource(R.drawable.ic_launcher);
        mNavigationDrawer.addHeaderView(header);
    }

    private List<NavigationItem> makeNavigationItems() {
        List<NavigationItem> items = new ArrayList<NavigationItem>();
        int count = 20;

        for (int i = 0; i < count; i++) {
            NavigationItem item = new NavigationItem();
            item.setIconDrawableId(R.drawable.ic_action_accessibility);
            item.setListName("ic_action_accessibility");
            items.add(item);
        }
        return items;
    }

    private void setUpAdapter() {
        List<NavigationItem> items = makeNavigationItems();
        NavigationDrawerListAdapter adapter = new NavigationDrawerListAdapter(this, R.layout.list_navigation_item, items);

        mNavigationDrawer.setAdapter(adapter);

        mNavigationDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Drawer", "onClick");
                mDrawerLayout.closeDrawer(mNavigationDrawer);
                GoogleMusicActivity.this.onDrawerItemSelected(position);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_google_photo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(int position) {
        Log.d("Drawer", "OnDrawerItemSelected: "+position);

    }
}
