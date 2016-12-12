package com.olfu.meis.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.olfu.meis.R;
import com.olfu.meis.adapter.LatestAdapter;
import com.olfu.meis.model.EarthquakeItem;

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
    LatestAdapter adapter;

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
        adapter = new LatestAdapter(items, this.getActivity());
        lvEQ.setAdapter(adapter);


    }



    private void showFilterDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.dialog_filter, null);
        builder.setView(view);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
