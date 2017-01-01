package com.example.shouvikshadman.dhakaevents;

/**
 * Created by Shouvik Shadman on 4/1/2016.
 */
public class EventList {
    private  String date;
    private  String description;
    private  String location;
    private  String name;
    private  String time;

    public EventList() {
        // empty default constructor, necessary for Firebase to be able to deserialize blog posts
    }

    public  String getName() {
        return name;
    }

    public  String getDate() {
        return date;
    }
    public  String getDescription() {
        return description;
    }
    public  String getLocation() {
        return location;
    }
    public  String getTime() {
        return time;
    }
}
