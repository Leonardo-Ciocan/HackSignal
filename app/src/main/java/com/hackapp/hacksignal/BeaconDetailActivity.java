package com.hackapp.hacksignal;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.ParseGeoPoint;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BeaconDetailActivity extends ActionBarActivity {

    @Bind(R.id.name)
    TextView name;

    @Bind(R.id.tech)
    TextView tech;

    @Bind(R.id.user)
    TextView user;

    @Bind(R.id.distance)
    TextView distance;


    @Bind(R.id.purpose)
    TextView purpose;


    @OnClick(R.id.navigate)
    void navigate(){
        String uri = String.format(Locale.ENGLISH, "geo:0,0?q=%f,%f", MainActivity.selected.getLocation().getLatitude()
                , MainActivity.selected.getLocation().getLongitude());
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    @Bind(R.id.onmyway)
    LinearLayout onmyway;

    @OnClick(R.id.onmyway)
    void myway(){
        Snackbar.make(findViewById(android.R.id.content) , MainActivity.selected.getUser().getString("name") +  " has been notified"  , Snackbar.LENGTH_LONG).show();
        onmyway.setVisibility(View.GONE);
    }


    @OnClick(R.id.contact)
    void contact(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, "barackobama@whitehouse.gov");
        intent.putExtra(Intent.EXTRA_SUBJECT, "I'm interested in your '" + MainActivity.selected.getName() + "' app");

        startActivity(Intent.createChooser(intent, "Send Email"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_detail);

        ButterKnife.bind(this);

        name.setText(MainActivity.selected.getName());
        tech.setText(MainActivity.selected.getTech());
        purpose.setText(MainActivity.selected.getPurpose());

        if(MainActivity.currentLocation != null) {
            ParseGeoPoint point = MainActivity.selected.getLocation();
            if(point!=null) {
                Location loc = new Location("");
                loc.setLatitude(point.getLatitude());
                loc.setLongitude(point.getLongitude());

                float dist = MainActivity.currentLocation.distanceTo(loc);
                distance.setText(String.valueOf(Math.round(dist)) + " meters");
            }
        }

        user.setText(MainActivity.selected.getUser().getString("name"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_beacon_detail, menu);
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
