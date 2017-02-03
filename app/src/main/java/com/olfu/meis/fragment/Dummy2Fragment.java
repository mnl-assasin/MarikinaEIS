package com.olfu.meis.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.olfu.meis.R;
import com.olfu.meis.adapter.EQAdapter;
import com.olfu.meis.api.ApiClient;
import com.olfu.meis.api.ApiInterface;
import com.olfu.meis.api.EarthquakeResponse;
import com.olfu.meis.model.EQItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static com.olfu.meis.model.EQItem.forecastMapItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dummy2Fragment extends Fragment {


    public static String TAG = "Dummy";

    @Bind(R.id.lvEQ)
    ListView lvEQ;

    EQAdapter adapter;
    ArrayList<EQItem> item;
    ArrayList<EQItem> forecastItem;

    public Dummy2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dummy, container, false);
        ButterKnife.bind(this, v);
        initEQ();
        requestEQ();
        setHasOptionsMenu(true);
        return v;
    }

    private void initEQ() {

        item = new ArrayList<>();
        forecastItem = new ArrayList<>();
        adapter = new EQAdapter(forecastItem, this.getActivity());
        lvEQ.setAdapter(adapter);

    }

    private void requestEQ() {

        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<List<EarthquakeResponse>> call = api.getForecast();
        call.enqueue(new Callback<List<EarthquakeResponse>>() {
            @Override
            public void onResponse(Call<List<EarthquakeResponse>> call, Response<List<EarthquakeResponse>> response) {
                List<EarthquakeResponse> list = response.body();
                populateList(list);
            }

            @Override
            public void onFailure(Call<List<EarthquakeResponse>> call, Throwable t) {

            }
        });

    }

    private void populateList(List<EarthquakeResponse> list) {
        for (int ctr = 0; ctr < list.size(); ctr++) {

            EarthquakeResponse eq = list.get(ctr);
            double magnitude = eq.getMagnitude();
            String location = eq.getAddress();
            double latitude = eq.getLatitude();
            double longitude = eq.getLongitude();
            String depth = String.valueOf(eq.getDepth());
            String timeStamp = eq.getDateTime();

            item.add(new EQItem(magnitude, location, latitude, longitude, depth, timeStamp));

        }

        Collections.sort(item);
        for (int ctr = 0; ctr < item.size(); ctr++) {
            Log.d(TAG, item.get(ctr).getTimeStamp());
        }

        forecastItem = item;
        forecastMapItem = item;

        adapter = new EQAdapter(forecastItem, this.getActivity());
        lvEQ.setAdapter(adapter);
        Log.d(TAG, "Adapter notify data set changed");

    }

}
