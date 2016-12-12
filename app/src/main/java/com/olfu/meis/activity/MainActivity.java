package com.olfu.meis.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.olfu.meis.R;
import com.olfu.meis.adapter.MainPagerAdapter;
import com.olfu.meis.sliding.SlidingTabLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.navigationView)
    NavigationView navigationView;

    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.tabs)
    SlidingTabLayout tabs;

    @Bind(R.id.pager)
    ViewPager pager;

    MainPagerAdapter adapter;
    CharSequence titles[] = {"Latest", "Map", "Prediction", "Stats"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

//        adapter = new MainPagerAdapter(getSupportFragmentManager(), titles);
//        pager.setAdapter(adapter);
//
//        tabs.setDistributeEvenly(true);
//        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
//            @Override
//            public int getIndicatorColor(int position) {
//                return getResources().getColor(R.color.colorAccent);
//            }
//        });
//        tabs.setViewPager(pager);
//
//        Calendar c = Calendar.getInstance();
//        Log.d("MAIN", "" + c.get(Calendar.DAY_OF_MONTH));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_settings:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
