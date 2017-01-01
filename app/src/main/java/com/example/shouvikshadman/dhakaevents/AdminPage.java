package com.example.shouvikshadman.dhakaevents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AdminPage extends AppCompatActivity {
    EditText name1,description1,time1,date1,location1;
    Firebase rootRef;
    Button bt1;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        Firebase.setAndroidContext(this);
        session = new SessionManager(getApplicationContext());
        rootRef = new Firebase("https://brilliant-fire-6231.firebaseio.com/");
        name1=(EditText)findViewById(R.id.editText3);
        description1=(EditText)findViewById(R.id.editText4);
        time1=(EditText)findViewById(R.id.editText5);
        date1=(EditText)findViewById(R.id.editText6);
        location1=(EditText)findViewById(R.id.editText7);
        bt1=(Button)findViewById(R.id.button2);
    }

    public void entryEvent(View view){
        String name=name1.getText().toString();
        String des=description1.getText().toString();
        String time=time1.getText().toString();
        String date=date1.getText().toString();
        String location=location1.getText().toString();
        String k="https://brilliant-fire-6231.firebaseio.com/EventList/"+date;
        rootRef = new Firebase(k);
        Firebase pushdata=rootRef.push();
        Map<String, String> post1 = new HashMap<String, String>();
        post1.put("name", name);
        post1.put("description", des);
        post1.put("time", time);
        post1.put("date", date);
        post1.put("location", location);
        pushdata.setValue(post1);

    }

    public void logout(View view){
        session.logoutUser();
    }






    /*public void entryEvent(View view){
        String name=name1.getText().toString();
        String des=description1.getText().toString();
        String time=time1.getText().toString();
        String date=date1.getText().toString();
        String location=location1.getText().toString();
        Firebase myThing= rootRef.child("Events");
        Firebase pushdata=myThing.push();
        Map<String, String> post1 = new HashMap<String, String>();
        post1.put("name", name);
        post1.put("description", des);
        post1.put("time", time);
        post1.put("date", date);
        post1.put("location", location);
        pushdata.setValue(post1);
        System.out.println(pushdata.getKey().toString());
        Toast toast = Toast.makeText(getApplicationContext(), "Success in Creating a new event!!!", Toast.LENGTH_SHORT);
        toast.show();

    }*/
}
