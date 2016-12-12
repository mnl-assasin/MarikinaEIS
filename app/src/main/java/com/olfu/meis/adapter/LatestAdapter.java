package com.olfu.meis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.olfu.meis.R;
import com.olfu.meis.model.EarthquakeItem;
import com.olfu.meis.utils.Distance;
import com.olfu.meis.utils.TimeHelper;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.attr.x;

/**
 * Created by mykelneds on 08/12/2016.
 */

public class LatestAdapter extends BaseAdapter {

    ArrayList<EarthquakeItem> items;
    Context mContext;
    LayoutInflater inflater;

    public LatestAdapter(ArrayList<EarthquakeItem> items, Context mContext) {
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

        ViewHolder holder;
        View v = inflater.inflate(R.layout.item_latest, viewGroup, false);
        holder = new ViewHolder(v);
        v.setTag(holder);

        EarthquakeItem item = items.get(i);

        holder.tvMagnitude.setText(String.valueOf(item.getMagnitude()));
        holder.tvLocation.setText(item.getLocation());

        double distance = Distance.distanceInMeters(lat, lon, item.getLatitude(), item.getLongitude());
        String unit;

        if (x > 1000) {
            distance  /= 1000;
            unit = " km";
        } else{
            unit = " m";
        }

        String AOE = "Depth: " + item.getDepth() + " Distance: " + String.format("%.2f", distance) + unit;
        holder.tvAOE.setText(AOE);

        Calendar calEQ = TimeHelper.setTime(item.getTimeStamp());
        holder.tvTimestamp.setText(TimeHelper.getTimeStamp(calEQ));

        return v;
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
