package com.olfu.meis.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.olfu.meis.fragment.FragmentMap;
import com.olfu.meis.fragment.LatestFragment;
import com.olfu.meis.fragment.PredictionFragment;
import com.olfu.meis.fragment.StatsFragment;


/**
 * Created by mleano on 10/25/2016.
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence titles[];

    public MainPagerAdapter(FragmentManager fm, CharSequence[] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new LatestFragment();
            case 1:
                return new FragmentMap();
//                return new MapFragment();
            case 2:
                return new PredictionFragment();
            case 3:
                return new StatsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
