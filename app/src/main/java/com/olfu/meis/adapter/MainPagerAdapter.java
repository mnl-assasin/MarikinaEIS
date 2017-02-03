package com.olfu.meis.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.olfu.meis.fragment.AftershockFragment;
import com.olfu.meis.fragment.Dummy2Fragment;
import com.olfu.meis.fragment.DummyFragment;


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
//                return new LatestFragment();
                return new DummyFragment();
//            case 2:
////                return new FragmentMap();
//                return new MapFragment();
            case 1:
                return new Dummy2Fragment();
//                return new PredictionFragment();
            case 2:
                return new AftershockFragment();
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
