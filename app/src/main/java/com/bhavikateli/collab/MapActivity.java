package com.bhavikateli.collab;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.ParseUser;

import org.parceler.Parcels;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    ParseUser user;
    GoogleMap map;
    Spinner spType;
    Button btnFind;
    SupportMapFragment mapFragment;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        user = Parcels.unwrap(getIntent().getParcelableExtra("user"));

        LatLng userLocation = new LatLng(user.getDouble("latitude"), user.getDouble("longitude"));
        map.addMarker( new MarkerOptions().position(userLocation).title(user.getUsername() + " Location"));
        map.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
    }


}