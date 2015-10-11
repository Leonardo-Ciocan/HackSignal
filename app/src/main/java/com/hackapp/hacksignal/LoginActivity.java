package com.hackapp.hacksignal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hackapp.hacksignal.models.Beacon;
import com.hackapp.hacksignal.models.WelcomeScreen;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;


public class LoginActivity extends ActionBarActivity {

    EditText userName;
    EditText passWord;
    Button   loginBtn;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ParseUser.registerSubclass(Beacon.class);
        // Enable Local Datastore.

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "siQADz4UoenmX5N4QDckRO9fbXBhOeFetwIKEvM0", "0ffORb3VV4u1u7ruXHPYRflfYLsFX3wqmtilWwZP");

        if(ParseUser.getCurrentUser() != null){
            Intent intent = new Intent(LoginActivity.this, WelcomeScreen.class);
            LoginActivity.this.startActivity(intent);
            finish();
        }

        userName = (EditText) findViewById(R.id.userName);
        passWord = (EditText) findViewById(R.id.usersPassword);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        registerBtn = (Button) findViewById(R.id.registerBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Login Screen: ", userName.getText().toString());
                Log.d("Login Screen: ", passWord.getText().toString());


                ParseUser.logInInBackground(userName.getText().toString(), passWord.getText().toString(),
                        new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if (e == null) {
                                    Intent intent = new Intent(LoginActivity.this, WelcomeScreen.class);
                                    LoginActivity.this.startActivity(intent);
                                } else {
                                    Toast.makeText(LoginActivity.this , "Cannot login" , Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                //commit

            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent register = new Intent(LoginActivity.this, ProfileEditActivity.class);

                LoginActivity.this.startActivity(register);

                //commit

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
