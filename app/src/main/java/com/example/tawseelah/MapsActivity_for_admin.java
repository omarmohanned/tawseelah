package com.example.tawseelah;

import android.os.Bundle;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity_for_admin extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_for_admin);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        final String longitude = getIntent().getStringExtra("longitude");
        final String latitude = getIntent().getStringExtra("latitude");
        final String name = getIntent().getStringExtra("name_for_marker");


        if ((longitude == null) && (latitude == null)) {
            LatLng iff = new LatLng(0, 0);
            mMap.addMarker(new MarkerOptions().position(iff).title(name));

        } else {

            double longi = Double.valueOf(longitude);
            double latit = Double.valueOf(latitude);
            Toast.makeText(getApplicationContext(), "tap on the marker to get additional information", Toast.LENGTH_LONG).show();
            LatLng res = new LatLng(latit, longi);

            mMap.addMarker(new MarkerOptions().position(res).title(name));
            mMap.addPolygon(new PolygonOptions().add(res).fillColor(2));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(res, 17));
            mMap.setMaxZoomPreference(40);

        }

    }
}
