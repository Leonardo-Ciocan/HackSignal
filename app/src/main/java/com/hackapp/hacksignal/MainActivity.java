package com.hackapp.hacksignal;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hackapp.hacksignal.models.Beacon;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends ActionBarActivity {
    @Bind(R.id.beacon_list)
    ListView beaconList;
    private BeaconAdapter adapter;

    public static Location currentLocation;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseUser.registerSubclass(Beacon.class);
        // Enable Local Datastore.

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "siQADz4UoenmX5N4QDckRO9fbXBhOeFetwIKEvM0", "0ffORb3VV4u1u7ruXHPYRflfYLsFX3wqmtilWwZP");


        try {
            ParseUser.logIn("leonardo","cake");
        } catch (ParseException e) {
            e.printStackTrace();
        }


        ButterKnife.bind(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        adapter = new BeaconAdapter(this,new ArrayList<Beacon>());


        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                map.setMyLocationEnabled(true);
            }
        });

                final LocationListener mLocationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(final Location location) {
                        Log.e("xapp", String.valueOf(location.getLatitude()));
                        currentLocation = location;
                        ParseQuery<Beacon> query = new ParseQuery<Beacon>("Beacon");
                        query.whereNear("location", new ParseGeoPoint(location.getLatitude(), location.getLongitude()));
                        query.findInBackground(new FindCallback<Beacon>() {
                            @Override
                            public void done(List<Beacon> objects, ParseException e) {
                                adapter = new BeaconAdapter(MainActivity.this, (ArrayList) objects);
                                beaconList.setAdapter(adapter);
                                map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15));

                            }
                        });
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                };


                LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10,
                        150, mLocationListener);

            }

            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_main, menu);
                return true;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();

                //noinspection SimplifiableIfStatement
                if (id == R.id.action_settings) {
                    return true;
                }

                return super.onOptionsItemSelected(item);
            }
        }
