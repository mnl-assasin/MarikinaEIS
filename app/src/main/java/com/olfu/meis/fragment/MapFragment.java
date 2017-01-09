package com.olfu.meis.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olfu.meis.model.LocationItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        Log.d("MAP", "ON CREATE " + LocationItem.latitude);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("MAP", "ON RESUME");

    }
}
