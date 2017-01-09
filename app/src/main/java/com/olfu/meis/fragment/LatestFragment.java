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
import com.olfu.meis.model.EarthquakeItem;
import com.olfu.meis.utils.Distance;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.olfu.meis.model.EarthquakeItem.getList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LatestFragment extends Fragment {

    @Bind(R.id.lvEQ)
    ListView lvEQ;


    ArrayList<EarthquakeItem> items;
    ArrayList<EarthquakeItem> tempItems;
    EQAdapter adapter;

    public LatestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_latest, container, false);
        ButterKnife.bind(this, v);
        setHasOptionsMenu(true);
        initEQ();
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

        items = getList();
        tempItems = items;
        adapter = new EQAdapter(tempItems, this.getActivity());
        lvEQ.setAdapter(adapter);

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

        Log.d("HELLO WORLD", location + " : " + date + " : " + magnitude);
        tempItems.clear();
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
        items = getList();
        Log.d("HW", locParam + " : " + magParam);

        for (int ctr = 0; ctr < items.size(); ctr++) {
            EarthquakeItem item = items.get(ctr);

            double distance = Distance.distanceInMeters(lat, lon, item.getLatitude(), item.getLongitude());
            Log.d("HW", "Distance: " + distance + " / Magnitude:" + magnitude);
            if (distance < locParam && item.getMagnitude() > magParam)
                tempItems.add(item);
        }

        adapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
