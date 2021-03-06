package com.olfu.meis.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.olfu.meis.R;
import com.olfu.meis.adapter.EQAdapter2;
import com.olfu.meis.api.ApiClient;
import com.olfu.meis.api.ApiInterface;
import com.olfu.meis.api.EarthquakeResponse;
import com.olfu.meis.model.EarthquakeItem2;
import com.olfu.meis.utils.TimeHelper2;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.olfu.meis.model.EarthquakeItem2.mapItemForecast;




/**
 * A simple {@link Fragment} subclass.
 */
public class PredictionFragment extends Fragment {


    @Bind(R.id.lvEQ)
    ListView lvEQ;


    ArrayList<EarthquakeItem2> tempItems;
    EQAdapter2 adapter;

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

        final ProgressDialog progressDialog = new ProgressDialog(this.getActivity());
        progressDialog.setMessage("downloading...");
        progressDialog.show();

        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<List<EarthquakeResponse>> call = api.getForecast();
        call.enqueue(new Callback<List<EarthquakeResponse>>() {
            @Override
            public void onResponse(Call<List<EarthquakeResponse>> call, Response<List<EarthquakeResponse>> response) {
                List<EarthquakeResponse> list = response.body();
                progressDialog.dismiss();
                populate(list);
            }


            @Override
            public void onFailure(Call<List<EarthquakeResponse>> call, Throwable t) {

            }
        });

    }

    private void populate(List<EarthquakeResponse> list) {
        tempItems = new ArrayList<>();
        for (int ctr = 0; ctr < list.size(); ctr++) {
            Log.d("TIME", TimeHelper2.convertDate(list.get(ctr).getDateTime()));
            EarthquakeResponse eq = list.get(ctr);
            double magnitude = eq.getMagnitude();
            String location = eq.getAddress();
            double latitude = eq.getLatitude();
            double longitude = eq.getLongitude();
            String depth = String.valueOf(eq.getDepth());
            String timeStamp = TimeHelper2.convertDate(list.get(ctr).getDateTime());
            boolean isControlVisible = false;
            tempItems.add(new EarthquakeItem2(magnitude, location, latitude, longitude, depth, timeStamp, isControlVisible));

        }

        mapItemForecast = tempItems;
        adapter = new EQAdapter2(tempItems, this.getActivity());
        lvEQ.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}


