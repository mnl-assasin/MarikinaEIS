package com.olfu.meis.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.olfu.meis.fragment.GuideFragment;

public class GuidePagerAdapter extends FragmentStatePagerAdapter {


    public GuidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        Bundle b = new Bundle();
        b.putInt("page", position);

        GuideFragment fragment = new GuideFragment();
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public int getCount() {
        return 11;
    }
}