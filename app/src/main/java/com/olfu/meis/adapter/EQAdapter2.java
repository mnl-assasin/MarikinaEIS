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
import com.olfu.meis.model.EarthquakeItem2;
import com.olfu.meis.utils.Distance;
import com.olfu.meis.utils.TimeHelper2;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mykelneds on 08/12/2016.
 */

public class EQAdapter2 extends BaseAdapter {

    ArrayList<EarthquakeItem2> items;
    Context mContext;
    LayoutInflater inflater;

    public EQAdapter2(ArrayList<EarthquakeItem2> items, Context mContext) {
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
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.item_latest, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        Log.d("EQarth", "position: " + i);
        if (i % 2 == 1)
            holder.layoutContainer.setBackgroundColor(mContext.getResources().getColor(R.color.colorEQCell2));

        final EarthquakeItem2 item = items.get(i);

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

        Calendar calEQ = TimeHelper2.setTime(item.getTimeStamp());
        holder.tvTimestamp.setText(TimeHelper2.getTimeStamp(calEQ));

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

        // onClick
//        holder.layoutContainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (item.isControlVisible()) {
//                    holder.layoutControls.setVisibility(View.GONE);
//                    item.setControlVisible(false);
//                } else {
//                    holder.layoutControls.setVisibility(View.VISIBLE);
//                    item.setControlVisible(true);
//                }
//
//            }
//        });
//
//        holder.layoutMap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LocationItem.latitude = item.getLatitude();
//                LocationItem.longitude = item.getLongitude();
//                MainActivity2.pager.setCurrentItem(2);
//            }
//        });
//
//        holder.layoutDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                /**
//                 * title: location
//                 * magnitude - Date;
//                 *
//                 * Description
//                 * Location: Lat long;
//                 * Depth:
//                 * Review Status: Reviewed
//                 * Source: Markina PDRRMO
//                 */
//
//                String title = item.getLocation() + "\nM" + item.getMagnitude() + TimeHelper2.dateWithGMT(TimeHelper2.setTime(item.getTimeStamp()));
//                String body = "Coordinates: " + item.getLatitude() + " \u00B0, " + item.getLongitude() + "\u00B0" + "\n" +
//                        "Location: " + item.getDepth()
//                        + "\nReview Status: Reviewed\nSource: Marikina PDRRMO";
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//                builder.setTitle(title).setMessage(body).setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                }).show();
//            }
//        });
//
//        holder.layoutShare.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


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
