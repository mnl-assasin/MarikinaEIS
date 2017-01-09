package com.olfu.meis.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.olfu.meis.R;
import com.olfu.meis.adapter.EQAdapter;
import com.olfu.meis.model.EarthquakeItem;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.olfu.meis.model.EarthquakeItem.getForecast;


/**
 * A simple {@link Fragment} subclass.
 */
public class PredictionFragment extends Fragment {


    @Bind(R.id.lvEQ)
    ListView lvEQ;


    ArrayList<EarthquakeItem> tempItems;
    EQAdapter adapter;

    public PredictionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);

        initEQ();
        return view;
    }


    private void initEQ() {

        tempItems = getForecast();
        adapter = new EQAdapter(tempItems, this.getActivity());
        lvEQ.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}


