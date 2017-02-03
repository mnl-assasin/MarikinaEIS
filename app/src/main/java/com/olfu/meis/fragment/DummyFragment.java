package com.olfu.meis.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;

import com.olfu.meis.R;
import com.olfu.meis.adapter.EQAdapter;
import com.olfu.meis.api.ApiClient;
import com.olfu.meis.api.ApiInterface;
import com.olfu.meis.api.EarthquakeResponse;
import com.olfu.meis.model.EQItem;
import com.olfu.meis.utils.Distance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.olfu.meis.model.EQItem.latestMapItem;
/**
 * A simple {@link Fragment} subclass.
 */
public class DummyFragment extends Fragment {

    public static String TAG = "Dummy";

    @Bind(R.id.lvEQ)
    ListView lvEQ;

    public DummyFragment() {
        // Required empty public constructor
    }

    EQAdapter adapter;
    ArrayList<EQItem> item;
    ArrayList<EQItem> latestItem;

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.latest_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_filter:
                showFilterDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initEQ() {

        item = new ArrayList<>();
        latestItem = new ArrayList<>();
        adapter = new EQAdapter(latestItem, this.getActivity());
        lvEQ.setAdapter(adapter);

    }

    private void requestEQ() {

        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<List<EarthquakeResponse>> call = api.getReports();
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

        latestItem = item;
        latestMapItem = item;

        adapter = new EQAdapter(latestItem, this.getActivity());
        lvEQ.setAdapter(adapter);
        Log.d(TAG, "Adapter notify data set changed");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void showFilterDialog() {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.dialog_filter, null);

        final Spinner spinLocation = (Spinner) view.findViewById(R.id.spinLocation);
        final Spinner spinDate = (Spinner) view.findViewById(R.id.spinDate);
        final Spinner spinMagnitude = (Spinner) view.findViewById(R.id.spinMagnitude);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                int location = spinLocation.getSelectedItemPosition();
                int date = spinDate.getSelectedItemPosition();
                int magnitude = spinMagnitude.getSelectedItemPosition();

                populateList(location, date, magnitude);
            }
        });
        builder.show();

    }

    private void populateList(int location, int date, int magnitude) {

        Log.d(TAG, location + " : " + date + " : " + magnitude);
//        latestItem.clear();
        double locParam = 99999;
        double magParam = 0.0;
        switch (location) {
            case 0:
                locParam = 1000;
                break;
            case 1:
                locParam = 2000;
                break;
            case 2:
                locParam = 3000;
                break;
            case 3:
                locParam = 99999;
                break;
        }

        switch (magnitude) {
            case 0:
                magParam = 1.0;
                break;
            case 1:
                magParam = 2.0;
                break;
            case 2:
                magParam = 3.0;
                break;
            case 3:
                magParam = 4.0;
                break;
            case 4:
                magParam = 5.0;
                break;
            case 5:
                magParam = 6.0;
                break;
            case 6:
                magParam = 7.0;
                break;

        }

        double lat = 14.647680;
        double lon = 121.116714;

//        items = getList();
        Log.d(TAG, locParam + " : " + magParam);

        latestItem = new ArrayList<>();
        Log.d(TAG, "ITEM SIZE: " + item.size());
        for (int ctr = 0; ctr < item.size(); ctr++) {
            EQItem eqItem = item.get(ctr);

            double distance = Distance.distanceInMeters(lat, lon, eqItem.getLatitude(), eqItem.getLongitude());
            Log.d(TAG, "Distance: " + distance + " / Magnitude:" + magParam + " EQ Magnitude: " + eqItem.getMagnitude());

            if (distance > locParam && eqItem.getMagnitude() > magParam) {
                Log.d(TAG, "Earthquake added");
                latestItem.add(eqItem);
            }

        }

        Collections.sort(latestItem);
        adapter = new EQAdapter(latestItem, this.getActivity());
        lvEQ.setAdapter(adapter);
//
//            double distance = Distance.distanceInMeters(lat, lon, eqItem.getLatitude(), eqItem.getLongitude());
//            Log.d("HW", "Distance: " + distance + " / Magnitude:" + magnitude);
//            if (distance < locParam && eqItem.getMagnitude() > magParam)
//                latestItem.add(eqItem);
//        }
//        Collections.sort(item);
//        adapter.notifyDataSetChanged();

    }


//    public String loanJSON() {
//        String json = null;
//
//        try {
//
//            InputStream is = getActivity().getAssets().open("dummy.json");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            json = new String(buffer, "UTF-8");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return json;
//    }

}
