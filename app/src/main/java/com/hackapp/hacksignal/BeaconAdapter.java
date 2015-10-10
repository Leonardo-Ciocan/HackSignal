package com.hackapp.hacksignal;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hackapp.hacksignal.models.Beacon;
import com.parse.ParseGeoPoint;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;

public class BeaconAdapter extends ArrayAdapter<Beacon> {
    public BeaconAdapter(Context context , ArrayList<Beacon> books){
        super(context , R.layout.beacon_list_item , books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate( R.layout.beacon_list_item , null);
            //convertView = new SquareView(getContext() , R.layout.book_tile);
        }

        TextView name = (TextView)convertView.findViewById(R.id.beacon_name);
        name.setText(getItem(position).getName());

        TextView purpose = (TextView)convertView.findViewById(R.id.beacon_purpose);
        purpose.setText(getItem(position).getPurpose());


        TextView tech = (TextView)convertView.findViewById(R.id.beacon_languages);
        tech.setText(getItem(position).getTech());


        TextView distance = (TextView)convertView.findViewById(R.id.beacon_distance);

        if(MainActivity.currentLocation != null) {
            ParseGeoPoint point = getItem(position).getLocation();
            if(point!=null) {
                Location loc = new Location("");
                loc.setLatitude(point.getLatitude());
                loc.setLongitude(point.getLongitude());

                float dist = MainActivity.currentLocation.distanceTo(loc);
                distance.setText(String.valueOf(dist) + " meters away");
            }
        }



        return convertView;
    }
}
