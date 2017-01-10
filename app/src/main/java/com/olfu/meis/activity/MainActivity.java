package com.olfu.meis.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.olfu.meis.R;
import com.olfu.meis.builder.DialogBuilder;
import com.olfu.meis.fragment.MainFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.fragmentLayout)
    FrameLayout fragmentLayout;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.navigationView)
    NavigationView navigationView;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initialized();
    }

    private void initialized() {

        setupToolbar();
        setupInitialView();
        setupNavigationView();

    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
    }

    private void setupInitialView() {
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.fragmentLayout, new MainFragment()).commit();

    }

    private void setupNavigationView() {

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
//                fragmentFrame.setTranslationX(slideOffset * drawerView.getWidth());
//                drawerLayout.bringChildToFront(fragmentFrame);
//                drawerLayout.requestLayout();
            }
        };


        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        String title = null;
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment mFragment = null;

        if (item.isChecked())
            item.setChecked(false);
        else
            item.setChecked(true);
        //if(item.isChecked())


        switch (item.getItemId()) {

        }


        if (mFragment != null) {
            mFragmentTransaction.replace(R.id.fragmentLayout, mFragment);
            mFragmentTransaction.commit();
            setTitle(title);
        }
        item.setChecked(true);
        drawerLayout.closeDrawers();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        DialogBuilder.dialogBuilder(MainActivity.this, getString(R.string.app_name),
                getString(R.string.app_exit), false, getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        MainActivity.this.finish();
                    }
                }, getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

    }


}
