package com.olfu.meis.fragment;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.olfu.meis.R;
import com.olfu.meis.model.EarthquakeItem;
import com.olfu.meis.utils.TimeHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import static com.olfu.meis.model.EarthquakeItem.getList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMap extends SupportMapFragment implements GoogleApiClient.ConnectionCallbacks,
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
    private final int CURRENT_MAP_TYPE = 0;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        initListeners();

        getMap().setMyLocationEnabled(true);
//        Location myLocation = getMyLocation();

//
        CameraPosition position = CameraPosition.builder()
                .target(new LatLng(14.646902, 121.120458))
                .zoom(13f)
                .bearing(0.0f)
                .tilt(0.0f)
                .build();
        getMap().animateCamera(CameraUpdateFactory
                .newCameraPosition(position), null);
        setupMarkers();

    }

    private void setupMarkers() {
        ArrayList<EarthquakeItem> items = getList();

        for (int ctr = 0; ctr < items.size(); ctr++) {
            EarthquakeItem item = items.get(ctr);
            LatLng position = new LatLng(item.getLatitude(), item.getLongitude());

            Calendar calEQ = TimeHelper.setTime(item.getTimeStamp());
            String snippet = "M" + item.getMagnitude() + " - " + TimeHelper.getTimeStamp(calEQ);

            getMap().addMarker(new MarkerOptions()
                    .position(position)
                    .title(item.getLocation())
                    .snippet(snippet)
                    .icon(BitmapDescriptorFactory.fromBitmap(markerCreator(item.getMagnitude())))
            );
        }
    }

    private Bitmap markerCreator(double magnitude) {
        int size = (int) (magnitude + 20) * 8;
        BitmapDrawable bitmapdraw;

        if (magnitude <= 4.0)
            bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.wave_moderate);
        else
            bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.wave_heavy);
        Bitmap b = bitmapdraw.getBitmap();

        Bitmap smallMarker = Bitmap.createScaledBitmap(b, size, size, false);
        return smallMarker;

    }

    ;


    private void initListeners() {
        getMap().setOnMarkerClickListener(this);
        getMap().setOnMapLongClickListener(this);
        getMap().setOnInfoWindowClickListener(this);
        getMap().setOnMapClickListener(this);
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

        getMap().animateCamera(CameraUpdateFactory
                .newCameraPosition(position), null);

        getMap().setMapType(MAP_TYPES[CURRENT_MAP_TYPE]);
        getMap().setTrafficEnabled(false);

        getMap().getUiSettings().setZoomControlsEnabled(true);

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
//        getMap().addMarker(options);
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
//        getMap().addMarker(options);
    }

    private String getAddressFromLatLng(LatLng latLng) {
        Geocoder geocoder = new Geocoder(getActivity());

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
