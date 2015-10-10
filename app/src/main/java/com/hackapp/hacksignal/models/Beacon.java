package com.hackapp.hacksignal.models;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Beacon")
public class Beacon extends ParseObject {
    public String getName(){
        return getString("name");
    }

    public void setName(String name){
        put("name",name);
    }

    public ParseGeoPoint getLocation(){
        return getParseGeoPoint("location");
    }

    public void setLocation(ParseGeoPoint point){
        put("location" , point);
    }

    public ParseUser getUser(){
        return getParseUser("user");
    }

    public void setUser(ParseUser user){
        put("user",user);
    }
}
