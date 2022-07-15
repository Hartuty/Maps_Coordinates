package com.abachapp.hava_mock.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abachapp.hava_mock.R;
import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Details extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.OnConnectionFailedListener, RoutingListener {

    String pickupdate, amount, drop, pick, dropofftime, distance, duration, carimage, driverimage, drivername, carname;
    TextView pickupdate_txt, amount_txt, dropofflocation_txt, pickuplocation_txt, dropofftime_txt, distance_txt, duration_txt, drivername_txt, carname_txt, pickuptime_txt, subtotal;
    ImageView carimage_txt, driverimage_txt;
    //google map object
    private GoogleMap mMap;
    String startlat,droplat,startlng,droplng;

    //current and destination location objects
    Location myLocation = null;
    Location destinationLocation = null;
    protected LatLng start = null;
    protected LatLng end = null;

    //to get location permissions.
    private final static int LOCATION_REQUEST_CODE = 23;
    boolean locationPermission = false;

    //polyline object
    private List<Polyline> polylines = null;
    MaterialButton button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        button=findViewById(R.id.backbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getdata();
        gettextviews();
        binddata();
        requestPermision();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        start=new LatLng(Double.valueOf(startlat),Double.valueOf(startlng));
        end=new LatLng(Double.valueOf(droplat),Double.valueOf(droplng));
        Findroutes(start,end);

    }

    private void getdata() {
        Intent intent = getIntent();
        pickupdate = intent.getStringExtra("pickupdate");
        amount = intent.getStringExtra("amount");
        drop = intent.getStringExtra("drop");
        pick = intent.getStringExtra("pick");
        dropofftime = intent.getStringExtra("dropofftime");
        distance = intent.getStringExtra("distance");
        duration = intent.getStringExtra("duration");
        carimage = intent.getStringExtra("carimage");
        driverimage = intent.getStringExtra("driverimage");
        drivername = intent.getStringExtra("drivername");
        carname = intent.getStringExtra("carname");
        startlat = intent.getStringExtra("startlat");
        droplat = intent.getStringExtra("droplat");
        startlng = intent.getStringExtra("startlng");
        droplng = intent.getStringExtra("droplng");

    }

    private void gettextviews() {
        pickupdate_txt = findViewById(R.id.rst_time);
        amount_txt = findViewById(R.id.rst_amount);
        dropofflocation_txt = findViewById(R.id.rst_destination);
        pickuplocation_txt = findViewById(R.id.rst_source);
        pickuptime_txt = findViewById(R.id.rst_source_time);
        dropofftime_txt = findViewById(R.id.rst_destination_time);
        distance_txt = findViewById(R.id.rst_distance);
        duration_txt = findViewById(R.id.rst_duration);
        drivername_txt = findViewById(R.id.rst_drivername);
        carname_txt = findViewById(R.id.rst_carname);
        carimage_txt = findViewById(R.id.rst_carimage);
        driverimage_txt = findViewById(R.id.rst_driverimage);
        subtotal = findViewById(R.id.rst_subtotal);
        Picasso.get().load(carimage).into(carimage_txt);
        Picasso.get().load(driverimage).into(driverimage_txt);
    }

    private void binddata() {
        pickupdate_txt.setText(pickupdate);
        amount_txt.setText(amount+ "KES");
        dropofflocation_txt.setText(drop);
        pickuplocation_txt.setText(pick);
        dropofftime_txt.setText(dropofftime);
        distance_txt.setText(distance+" KM");
        duration_txt.setText(duration+" min");
        carname_txt.setText(carname);
        drivername_txt.setText(drivername);
        subtotal.setText(amount+ "KES");

    }

    private void requestPermision() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_REQUEST_CODE);
        } else {
            locationPermission = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //if permission granted.
                    locationPermission = true;
                    getMyLocation();

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

    //to get user location
    private void getMyLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(start, 14f);
        mMap.animateCamera(cameraUpdate);
        Findroutes(start,end);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        getMyLocation();

    }


    // function to find Routes.
    public void Findroutes(LatLng Start, LatLng End)
    {
        if(Start==null || End==null) {
            Toast.makeText(Details.this,"Unable to get location",Toast.LENGTH_LONG).show();
        }
        else
        {

            Routing routing = new Routing.Builder()
                    .travelMode(AbstractRouting.TravelMode.DRIVING)
                    .withListener(this)
                    .alternativeRoutes(true)
                    .waypoints(Start, End)
                    .key("Your google api key")  //also define your api key here.
                    .build();
            routing.execute();
        }
    }

    //Routing call back functions.
    @Override
    public void onRoutingFailure(RouteException e) {
        View parentLayout = findViewById(android.R.id.content);
        Snackbar snackbar= Snackbar.make(parentLayout, e.toString(), Snackbar.LENGTH_LONG);
        snackbar.show();
//    Findroutes(start,end);
    }

    @Override
    public void onRoutingStart() {
        Toast.makeText(Details.this,"Finding Route...",Toast.LENGTH_LONG).show();
    }

    //If Route finding success..
    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {

        CameraUpdate center = CameraUpdateFactory.newLatLng(start);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);
        if(polylines!=null) {
            polylines.clear();
        }
        PolylineOptions polyOptions = new PolylineOptions();
        LatLng polylineStartLatLng=null;
        LatLng polylineEndLatLng=null;


        polylines = new ArrayList<>();
        //add route(s) to the map using polyline
        for (int i = 0; i <route.size(); i++) {

            if(i==shortestRouteIndex)
            {
                polyOptions.color(Color.GREEN);
                polyOptions.width(8);
                polyOptions.addAll(route.get(shortestRouteIndex).getPoints());
                Polyline polyline = mMap.addPolyline(polyOptions);
                polylineStartLatLng=polyline.getPoints().get(0);
                int k=polyline.getPoints().size();
                polylineEndLatLng=polyline.getPoints().get(k-1);
                polylines.add(polyline);

            }
            else {

            }

        }

        //Add Marker on route starting position
        Marker marker = mMap.addMarker(new MarkerOptions().position(start).title("PickUp"));
        marker.showInfoWindow();


        //Add Marker on route ending position
        Marker marker2 = mMap.addMarker(new MarkerOptions().position(polylineEndLatLng).title("DropOff"));
        marker2.showInfoWindow();


    }

    @Override
    public void onRoutingCancelled() {
        Findroutes(start,end);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Findroutes(start,end);

    }

    }