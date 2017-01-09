package com.olfu.meis.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.olfu.meis.R;
import com.olfu.meis.fragment.MainFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

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
    }


}
