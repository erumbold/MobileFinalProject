package com.rumboldfabbro.mobilefinalproject;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    private GoogleMap mMap;
    //TODO get lat and long from college db table
    public Database db = new Database(getApplicationContext());


    private static final LatLng UDEL = new LatLng(39.678776, -75.750611);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ArrayList<Database> markersArray = new ArrayList<Database>();

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        for (int i =0; i<6; i++){
            db.getLat("Colleges",i);
            db.getLong("Colleges",i);
            //TODO fix createMarker, we need to remove getIconResID
            createMarker(markersArray.get(i).getLat("Colleges",i), markersArray.get(i).getLong("Colleges",i),
                    markersArray.get(i).getName("Colleges",i), markersArray.get(i).getAddress("College",i));
        }


        Marker mUDel = mMap.addMarker(new MarkerOptions()
                .position(UDEL)
                .title("University of Delaware"));
        mUDel.setTag(0);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(UDEL));

    }

    protected Marker createMarker(double latitude, double longitude, String title, String snippet) {

        return mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title)
                .snippet(snippet));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}
