package com.olfu.meis.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olfu.meis.R;
import com.olfu.meis.adapter.MainPagerAdapter;
import com.olfu.meis.sliding.SlidingTabLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    MainPagerAdapter adapter;
    CharSequence titles[] = {"Latest" , "Forecast", "Aftershock"};
    @Bind(R.id.tabs)
    SlidingTabLayout tabs;
    @Bind(R.id.pager)
    ViewPager pager;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        setupPager();
        return view;
    }

    private void setupPager() {


        adapter = new MainPagerAdapter(getChildFragmentManager(), titles);
        pager.setAdapter(adapter);
        tabs.setDistributeEvenly(false);
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorAccent);
            }
        });
        tabs.setViewPager(pager);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
