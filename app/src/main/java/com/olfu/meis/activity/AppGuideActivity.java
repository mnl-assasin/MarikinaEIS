package com.olfu.meis.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.olfu.meis.R;
import com.olfu.meis.adapter.GuidePagerAdapter;
import com.olfu.meis.data.EZSharedPreferences;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppGuideActivity extends AppCompatActivity {

    @Bind(R.id.pager)
    ViewPager pager;
    @Bind(R.id.btnSkip)
    Button btnSkip;
    @Bind(R.id.activity_app_guide)
    RelativeLayout activityAppGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_app_guide);
        ButterKnife.bind(this);


//        CustomPagerAdapter adapter = new CustomPagerAdapter(getApplicationContext());
        GuidePagerAdapter adapter = new GuidePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setPageTransformer(false, new CardTransformer());


        new Handler().post(runnable);


    }


    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (pager.getCurrentItem() < 11) {
                        pager.setCurrentItem(pager.getCurrentItem() + 1);
                        new Handler().post(runnable);
                    }
                    else{

                    }

                }
            }, 1000);
        }
    };


    @OnClick(R.id.btnSkip)
    public void onClick() {

        EZSharedPreferences.setFTU(AppGuideActivity.this);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        EZSharedPreferences.setFTU(AppGuideActivity.this);
        finish();
    }
}
