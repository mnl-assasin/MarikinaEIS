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
import com.olfu.meis.adapter.EQAdapter;
import com.olfu.meis.api.ApiClient;
import com.olfu.meis.api.ApiInterface;
import com.olfu.meis.api.EarthquakeModel;
import com.olfu.meis.model.EarthquakeItem;
import com.olfu.meis.utils.TimeHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

        final ProgressDialog progressDialog = new ProgressDialog(this.getActivity());
        progressDialog.setMessage("downloading...");
        progressDialog.show();

        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<List<EarthquakeModel>> call = api.getForecast();
        call.enqueue(new Callback<List<EarthquakeModel>>() {
            @Override
            public void onResponse(Call<List<EarthquakeModel>> call, Response<List<EarthquakeModel>> response) {
                List<EarthquakeModel> list = response.body();
                progressDialog.dismiss();
                populate(list);
            }


            @Override
            public void onFailure(Call<List<EarthquakeModel>> call, Throwable t) {

            }
        });

    }

    private void populate(List<EarthquakeModel> list) {
        tempItems = new ArrayList<>();
        for (int ctr = 0; ctr < list.size(); ctr++) {
            Log.d("TIME", TimeHelper.convertDate(list.get(ctr).getDateTime()));
            EarthquakeModel eq = list.get(ctr);
            double magnitude = eq.getMagnitude();
            String location = eq.getAddress();
            double latitude = eq.getLatitude();
            double longitude = eq.getLongitude();
            String depth = String.valueOf(eq.getDepth());
            String timeStamp = TimeHelper.convertDate(list.get(ctr).getDateTime());
            boolean isControlVisible = false;
            tempItems.add(new EarthquakeItem(magnitude, location, latitude, longitude, depth, timeStamp, isControlVisible));

        }

        adapter = new EQAdapter(tempItems, this.getActivity());
        lvEQ.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}


