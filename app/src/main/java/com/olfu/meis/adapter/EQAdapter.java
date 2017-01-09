package com.olfu.meis.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.olfu.meis.R;
import com.olfu.meis.activity.MainActivity2;
import com.olfu.meis.model.EarthquakeItem;
import com.olfu.meis.model.LocationItem;
import com.olfu.meis.utils.Distance;
import com.olfu.meis.utils.TimeHelper;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mykelneds on 08/12/2016.
 */

public class EQAdapter extends BaseAdapter {

    ArrayList<EarthquakeItem> items;
    Context mContext;
    LayoutInflater inflater;

    public EQAdapter(ArrayList<EarthquakeItem> items, Context mContext) {
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

        final EarthquakeItem item = items.get(i);

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

        Calendar calEQ = TimeHelper.setTime(item.getTimeStamp());
        holder.tvTimestamp.setText(TimeHelper.getTimeStamp(calEQ));

        if (item.isControlVisible())
            holder.layoutControls.setVisibility(View.VISIBLE);
        else
            holder.layoutControls.setVisibility(View.GONE);

        // onClick
        holder.layoutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.isControlVisible()) {
                    holder.layoutControls.setVisibility(View.GONE);
                    item.setControlVisible(false);
                } else {
                    holder.layoutControls.setVisibility(View.VISIBLE);
                    item.setControlVisible(true);
                }

            }
        });

        holder.layoutMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocationItem.latitude = item.getLatitude();
                LocationItem.longitude = item.getLongitude();
                MainActivity2.pager.setCurrentItem(2);
            }
        });

        holder.layoutDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * title: location
                 * magnitude - Date;
                 *
                 * Description
                 * Location: Lat long;
                 * Depth:
                 * Review Status: Reviewed
                 * Source: Markina PDRRMO
                 */

                String title = item.getLocation() + "\nM" + item.getMagnitude() + TimeHelper.dateWithGMT(TimeHelper.setTime(item.getTimeStamp()));
                String body = "Coordinates: " + item.getLatitude() + " \u00B0, " + item.getLongitude() + "\u00B0" + "\n" +
                        "Location: " + item.getDepth()
                        + "\nReview Status: Reviewed\nSource: Marikina PDRRMO";

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle(title).setMessage(body).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
            }
        });

        holder.layoutShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

        @Bind(R.id.layoutMap)
        LinearLayout layoutMap;
        @Bind(R.id.layoutDetails)
        LinearLayout layoutDetails;
        @Bind(R.id.layoutShare)
        LinearLayout layoutShare;
        @Bind(R.id.layoutControls)
        LinearLayout layoutControls;
        @Bind(R.id.layoutContainer)
        LinearLayout layoutContainer;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
