package com.olfu.meis.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.olfu.meis.R;
import com.olfu.meis.activity.MapActivity;
import com.olfu.meis.builder.DialogBuilder;
import com.olfu.meis.model.EQItem;
import com.olfu.meis.utils.Distance;
import com.olfu.meis.utils.TimeHelper;
import com.olfu.meis.utils.TimeHelper2;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mykelneds on 03/02/2017.
 */

public class EQAdapter extends BaseAdapter {

    private static final String TAG = "EQAdapter";

    ArrayList<EQItem> items;
    Context mContext;
    LayoutInflater inflater;

    public EQAdapter(ArrayList<EQItem> items, Context mContext) {
        this.items = items;
        this.mContext = mContext;

        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        double lat = 14.647680;
        double lon = 121.116714;

        final ViewHolder holder;

        if (view != null)
            holder = (ViewHolder) view.getTag();
        else {
            view = inflater.inflate(R.layout.item_latest, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        Log.d(TAG, "position: " + (i % 2));
        if (i == 0)
            holder.layoutContainer.setBackgroundColor(mContext.getResources().getColor(R.color.colorEQCell));
        else if (i % 2 == 1)
            holder.layoutContainer.setBackgroundColor(mContext.getResources().getColor(R.color.colorEQCell2));

        final EQItem item = items.get(i);

        holder.tvMagnitude.setText(String.valueOf(item.getMagnitude()));
        holder.tvLocation.setText(item.getLocation());

        double distance = Distance.distanceInMeters(lat, lon, item.getLatitude(), item.getLongitude());
        String unit;

        if (distance > 1000) {
            distance /= 1000;
            unit = " km";
        } else {
            unit = " m";
        }

        String AOE = "Depth: " + item.getDepth() + " Distance: " + String.format("%.2f", distance) + unit;
        holder.tvAOE.setText(AOE);
        holder.tvTimestamp.setText(TimeHelper.getTimeStamp(item.getTimeStamp()));

        holder.layoutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogBuilder.dialogBuilder(mContext, "Select Action", "Map it!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Bundle bundle = new Bundle();
                        bundle.putDouble("latitude", item.getLatitude());
                        bundle.putDouble("longitude", item.getLongitude());


                        Intent intent = new Intent(mContext, MapActivity.class);
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);
                    }
                }, "Details", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String title = item.getLocation() + "\nM" + item.getMagnitude() + TimeHelper2.dateWithGMT(TimeHelper2.setTime(item.getTimeStamp()));
                        String body = "Coordinates: " + item.getLatitude() + " \u00B0, " + item.getLongitude() + "\u00B0" + "\n" +
                                "Location: " + item.getDepth()
                                + "\nReview Status: Reviewed\nSource: Marikina PDRRMO";

                        DialogBuilder.dialogBuilder(mContext, title, body);
                    }
                });
            }
        });

        return view;
    }

    static class ViewHolder {

        @Bind(R.id.tvMagnitude)
        TextView tvMagnitude;
        @Bind(R.id.tvTimestamp)
        TextView tvTimestamp;
        @Bind(R.id.tvLocation)
        TextView tvLocation;
        @Bind(R.id.tvAOE)
        TextView tvAOE;

        LinearLayout layoutControls;
        @Bind(R.id.layoutContainer)
        LinearLayout layoutContainer;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
