package com.hackapp.hacksignal.models;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.hackapp.hacksignal.MainActivity;
import com.hackapp.hacksignal.R;
import com.parse.ParseUser;

public class WelcomeScreen extends ActionBarActivity {

    Button createBeacon;
    Button exploreBeacon;
    Animation myAnimation , myAnimation2;
    TextView welcomeName;
    TextView textView2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        exploreBeacon = (Button) findViewById(R.id.exploreBeaconBtn);
        welcomeName = (TextView) findViewById(R.id.welcomeName);
        welcomeName.setText(ParseUser.getCurrentUser().getUsername());

        exploreBeacon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeScreen.this, MainActivity.class);
                WelcomeScreen.this.startActivity(intent);
            }
        });


    }

}
