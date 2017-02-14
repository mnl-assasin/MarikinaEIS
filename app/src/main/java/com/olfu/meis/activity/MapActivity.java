package com.olfu.meis.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.olfu.meis.R;
import com.olfu.meis.model.EQItem;
import com.olfu.meis.model.LocationItem;
import com.olfu.meis.utils.TimeHelper2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.ButterKnife;

import static com.olfu.meis.model.EQItem.aftershockMapItem;
import static com.olfu.meis.model.EQItem.forecastMapItem;
import static com.olfu.meis.model.EQItem.latestMapItem;
import static com.olfu.meis.model.LocationItem.latitude;

public class MapActivity extends AppCompatActivity

        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMapLongClickListener,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMarkerClickListener {

    private GoogleApiClient mGoogleApiClient;
    private Location mCurrentLocation;

    private final int[] MAP_TYPES = {GoogleMap.MAP_TYPE_SATELLITE,
            GoogleMap.MAP_TYPE_NORMAL,
            GoogleMap.MAP_TYPE_HYBRID,
            GoogleMap.MAP_TYPE_TERRAIN,
            GoogleMap.MAP_TYPE_NONE};
    private final int CURRENT_MAP_TYPE = 2;

    GoogleMap map;
    double x = 0;
    double y = 0;

//    @Bind(R.id.toolbar)
//    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);

        createWidgets();
        initFaultLine();
        initData();
        initMarkers();
    }

    private void initFaultLine() {
        PolylineOptions line =
                new PolylineOptions().add(new LatLng(14.614033, 121.074545),
                        new LatLng(14.619664, 121.076777),
                        new LatLng(14.633078, 121.084495),
                        new LatLng(14.646718, 121.120441),
                        new LatLng(14.660145, 121.126660))
                        .width(8).color(Color.RED);

        map.addPolyline(line);
    }

    private void createWidgets() {

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();

        map.setMyLocationEnabled(true);
        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        map.setMapType(MAP_TYPES[CURRENT_MAP_TYPE]);


    }

    private void initData() {

        initListeners();
        Bundle b = getIntent().getExtras();
        if (b != null) {
            double lat = b.getDouble("latitude");
            double lon = b.getDouble("longitude");

            x = lat;
            y = lon;
            CameraPosition position = CameraPosition.builder()
                    .target(new LatLng(lat, lon))
                    .zoom(15f)
                    .bearing(0.0f)
                    .tilt(0.0f)
                    .build();
            map.animateCamera(CameraUpdateFactory
                    .newCameraPosition(position), null);

        } else {
            CameraPosition position = CameraPosition.builder()
                    .target(new LatLng(14.646902, 121.120458))
                    .zoom(13f)
                    .bearing(0.0f)
                    .tilt(0.0f)
                    .build();
            map.animateCamera(CameraUpdateFactory
                    .newCameraPosition(position), null);
        }
    }

    private void initListeners() {
        map.setOnMarkerClickListener(this);
        map.setOnMapLongClickListener(this);
        map.setOnInfoWindowClickListener(this);
        map.setOnMapClickListener(this);
    }

    private void initMarkers() {
        ArrayList<EQItem> items = latestMapItem;

        for (int ctr = 0; ctr < items.size(); ctr++) {
            EQItem item = items.get(ctr);
            LatLng position = new LatLng(item.getLatitude(), item.getLongitude());

            Calendar calEQ = TimeHelper2.setTime(item.getTimeStamp());
            String snippet = "M" + item.getMagnitude() + " - " + TimeHelper2.getTimeStamp(calEQ);


            if (x == item.getLatitude() && item.getLongitude() == y) {
                map.addMarker(new MarkerOptions()
                        .position(position)
                        .title(item.getLocation())
                        .snippet(snippet)
                        .icon(BitmapDescriptorFactory.fromBitmap(markerCreator(item.getMagnitude(), 0)))
                ).showInfoWindow();
            } else {
                map.addMarker(new MarkerOptions()
                        .position(position)
                        .title(item.getLocation())
                        .snippet(snippet)
                        .icon(BitmapDescriptorFactory.fromBitmap(markerCreator(item.getMagnitude(), 0)))
                );

            }

        }

        items = forecastMapItem;
        for (int ctr = 0; ctr < items.size(); ctr++) {
            EQItem item = items.get(ctr);
            LatLng position = new LatLng(item.getLatitude(), item.getLongitude());

            Calendar calEQ = TimeHelper2.setTime(item.getTimeStamp());
            String snippet = "M" + item.getMagnitude() + " - " + TimeHelper2.getTimeStamp(calEQ);

            if (x == item.getLatitude() && item.getLongitude() == y) {
                map.addMarker(new MarkerOptions()
                        .position(position)
                        .title(item.getLocation())
                        .snippet(snippet)
                        .icon(BitmapDescriptorFactory.fromBitmap(markerCreator(item.getMagnitude(), 1)))
                ).showInfoWindow();
            } else {
                map.addMarker(new MarkerOptions()
                        .position(position)
                        .title(item.getLocation())
                        .snippet(snippet)
                        .icon(BitmapDescriptorFactory.fromBitmap(markerCreator(item.getMagnitude(), 1)))
                );

            }
        }

        items = aftershockMapItem;
        for (int ctr = 0; ctr < items.size(); ctr++) {
            EQItem item = items.get(ctr);
            LatLng position = new LatLng(item.getLatitude(), item.getLongitude());

            Calendar calEQ = TimeHelper2.setTime(item.getTimeStamp());
            String snippet = "M" + item.getMagnitude() + " - " + TimeHelper2.getTimeStamp(calEQ);

            if (x == item.getLatitude() && item.getLongitude() == y) {
                map.addMarker(new MarkerOptions()
                        .position(position)
                        .title(item.getLocation())
                        .snippet(snippet)
                        .icon(BitmapDescriptorFactory.fromBitmap(markerCreator(item.getMagnitude(), 2)))
                ).showInfoWindow();
            } else {
                map.addMarker(new MarkerOptions()
                        .position(position)
                        .title(item.getLocation())
                        .snippet(snippet)
                        .icon(BitmapDescriptorFactory.fromBitmap(markerCreator(item.getMagnitude(), 2)))
                );

            }
        }


        if (latitude != 0 & LocationItem.longitude != 0) {
//
            CameraPosition position = CameraPosition.builder()
                    .target(new LatLng(latitude, latitude))
                    .zoom(15f)
                    .bearing(0.0f)
                    .tilt(0.0f)
                    .build();
            map.animateCamera(CameraUpdateFactory
                    .newCameraPosition(position), null);

            latitude = 0;
            LocationItem.longitude = 0;
        }
    }

    private Bitmap markerCreator(double magnitude, int type) {
//        int size = (int) (magnitude + 25) * 8;
        BitmapDrawable bitmapdraw;
        int size = 75;
//        if (magnitude <= 4.0)
//            bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.wave_moderate);
//        else
//

        if (type == 0) {
            bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.wave_heavy);
        } else if (type == 1) {
            bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.wave_forecast);
        } else {
            bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.wave_moderate);
        }
        Bitmap b = bitmapdraw.getBitmap();

        Bitmap smallMarker = Bitmap.createScaledBitmap(b, size, size, false);
        return smallMarker;

    }


    @Override
    public void onConnected(Bundle bundle) {
        Log.d("Location", "onConnected");
        mCurrentLocation = LocationServices
                .FusedLocationApi
                .getLastLocation(mGoogleApiClient);

        initCamera(mCurrentLocation);
    }

    private void initCamera(Location location) {
        CameraPosition position = CameraPosition.builder()
                .target(new LatLng(location.getLatitude(),
                        location.getLongitude()))
                .zoom(16f)
                .bearing(0.0f)
                .tilt(0.0f)
                .build();

        map.animateCamera(CameraUpdateFactory
                .newCameraPosition(position), null);

        map.setMapType(MAP_TYPES[CURRENT_MAP_TYPE]);
        map.setTrafficEnabled(false);

        map.getUiSettings().setZoomControlsEnabled(true);

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("Location", "Suspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("Location", "FAILED");
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public void onMapClick(LatLng latLng) {
//        MarkerOptions options = new MarkerOptions().position(latLng);
//        options.title(getAddressFromLatLng(latLng));
//
//        options.icon(BitmapDescriptorFactory.defaultMarker());
//        map.addMarker(options);
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
//        MarkerOptions options = new MarkerOptions().position(latLng);
//        options.title(getAddressFromLatLng(latLng));
//
//        options.icon(BitmapDescriptorFactory.fromBitmap(
//                BitmapFactory.decodeResource(getResources(),
//                        R.mipmap.ic_launcher)));
//
//        map.addMarker(options);
    }

    private String getAddressFromLatLng(LatLng latLng) {
        Geocoder geocoder = new Geocoder(getApplicationContext());

        String address = "";
        try {
            address = geocoder
                    .getFromLocation(latLng.latitude, latLng.longitude, 1)
                    .get(0).getAddressLine(0);
        } catch (IOException e) {
        }

        return address;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return true;
    }
}
