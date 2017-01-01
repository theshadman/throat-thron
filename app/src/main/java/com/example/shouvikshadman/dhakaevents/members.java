package com.example.shouvikshadman.dhakaevents;

/**
 * Created by Shouvik Shadman on 3/27/2016.
 */
public class members {
    private  String name;
    private  String password;
    public members() {
        // empty default constructor, necessary for Firebase to be able to deserialize blog posts
    }

    public  String getName() {
        return name;
    }

    public  String getPassword() {
        return password;
    }


}

