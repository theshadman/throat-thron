package com.example.shouvikshadman.dhakaevents;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.sax.StartElementListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class OpeningScreenActivity extends AppCompatActivity {
    //SaveSharedPreference s=new SaveSharedPreference();
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_opening_screen);
        session = new SessionManager(getApplicationContext());
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 2s = 2000ms
                if(session.getName().equals("name"))
                    goToSignIn();
                else
                    goToEventList();
            }
        }, 2000);
    }

    public void goToEventList(){
        Intent i=new Intent(this,EventListActivity.class);
        startActivity(i);
    }
    public void goToSignIn(){
        Intent i=new Intent(this,SigningActivity.class);
        startActivity(i);
    }
    public void goToAdmin(){
        Intent i=new Intent(this,AdminPage.class);
        startActivity(i);
    }

}
