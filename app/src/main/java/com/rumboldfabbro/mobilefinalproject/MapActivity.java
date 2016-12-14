package com.rumboldfabbro.mobilefinalproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

        SQLiteDatabase data = db.getReadableDatabase();
        String[] projection = {"Name", "Address", "Latitude", "Longitude"};
        String selection = "ID != ?";
        String[] selectionArgs = {""};
        String sortOrder = "ID";

        Cursor c = data.query("Colleges", projection, selection, selectionArgs, null, null, sortOrder);
        c.moveToFirst();

        for (int i =1; i<6; i++){
            String name = c.getString(c.getColumnIndex("Name"));
            String address = c.getString(c.getColumnIndex("Address"));
            double lat = c.getDouble(c.getColumnIndex("Latitude"));
            double lng = c.getDouble(c.getColumnIndex("Longitude"));
            //TODO fix createMarker, we need to remove getIconResID
            createMarker(lat, lng, name, address);
            c.moveToNext();
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
