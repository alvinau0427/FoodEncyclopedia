package com.foodangel;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class EventLocationFragment extends Fragment {

    MainActivity m = new MainActivity();
    String path = m.getPath();

    String url = null;

    View rootView;
    MapView mView;
    ActionBar actionBar;

    double longitude;
    double latitude;

    Location location;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_event_location, container, false);
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        actionBar.setTitle(getString(R.string.navigation_location));

        mView = (MapView) rootView.findViewById(R.id.mView);
        mView.onCreate(savedInstanceState);

//        locMgr = (LocationManager) getActivity().getSystemService(getContext().LOCATION_SERVICE);
//        Criteria criteria = new Criteria();
//        bestProvider = locMgr.getBestProvider(criteria, true);
//        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return rootView;
//        }
//        location = locMgr.getLastKnownLocation(bestProvider);
        mView.onResume();
        showMap(location);
        return rootView;
    }

    private void showMap(Location location) {
        longitude = 22.319647;
        latitude = 114.208454;

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                googleMap.addMarker(new MarkerOptions().position(
                        new LatLng(22.330876, 114.160114)).title("食物收集大行動 (西九龍中心)")
                        .snippet("日期：11月2日, 時間：12:00-21:00")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

                googleMap.addMarker(new MarkerOptions().position(
                        new LatLng(22.319647, 114.208454)).title("食物回收及宣傳活動 (MegaBox)")
                        .snippet("日期：11月20日, 時間：10:00-21:00")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(longitude, latitude)).zoom(11).build();
                googleMap.animateCamera(CameraUpdateFactory
                        .newCameraPosition(cameraPosition));
                googleMap.getUiSettings().setZoomControlsEnabled(true);
            }
        });
    }
}