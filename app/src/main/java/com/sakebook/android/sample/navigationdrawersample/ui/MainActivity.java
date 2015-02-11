package com.sakebook.android.sample.navigationdrawersample.ui;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
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
import android.widget.ImageView;
import android.widget.ListView;

import com.sakebook.android.sample.navigationdrawersample.DrawerCallback;
import com.sakebook.android.sample.navigationdrawersample.adapter.NavigationDrawerListAdapter;
import com.sakebook.android.sample.navigationdrawersample.model.NavigationItem;
import com.sakebook.android.sample.navigationdrawersample.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity
        implements DrawerCallback {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ListView mNavigationDrawer;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity);

        initLayout();
        // tool bar設定
        settingToolbar();
        setUpHeaderView();
        setUpAdapter();


//        mNavigationDrawerFragment = (NavigationDrawerFragment2)
//                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
//        mTitle = getTitle();
//
//        // Set up the drawer.
//        mNavigationDrawerFragment.setUp(
//                R.id.navigation_drawer,
//                (DrawerLayout) findViewById(R.id.drawer_layout));
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
        mDrawerLayout.setStatusBarBackground(R.drawable.ic_launcher);
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
        int count = 15;

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
                if (position == 0) {
                    mDrawerLayout.closeDrawer(mNavigationDrawer);
                } else if (position == 1) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.closeDrawers();
                }
                MainActivity.this.onDrawerItemSelected(0);
            }
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
//        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerLayout);
        return super.onPrepareOptionsMenu(menu);
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        if (!mDrawerLayout.isDrawerOpen(mNavigationDrawer)) {
//            // Only show items in the action bar relevant to this screen
//            // if the drawer is not showing. Otherwise, let the drawer
//            // decide what to show in the action bar.
//            getMenuInflater().inflate(R.menu.main_activity2, menu);
//            restoreActionBar();
//            return true;
//        }
        return super.onCreateOptionsMenu(menu);
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

        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_activity, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
