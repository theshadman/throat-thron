package com.example.shouvikshadman.dhakaevents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EventListActivity extends AppCompatActivity {
    public ListView mainListView ;
    public ArrayList<String> items;
    TextView tv;
    Firebase rootRef;
    public String dating;
    SessionManager session;
    public int count=1,day=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        Firebase.setAndroidContext(this);
        tv=(TextView)findViewById(R.id.textView5);
        session = new SessionManager(getApplicationContext());
        mainListView=(ListView)findViewById(R.id.listView);
        items=new ArrayList<String>();
        // ListView listView = (ListView) findViewById(R.id.listView);

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());
        System.out.println("Current time => " + formattedDate);
        dating=formattedDate;
        tv.setText(dating);
        df = new SimpleDateFormat("dd");
        day=Integer.parseInt(df.format(c.getTime()));
        rootRef = new Firebase("https://brilliant-fire-6231.firebaseio.com/EventList/"+formattedDate);
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");
                for (DataSnapshot p : snapshot.getChildren()) {
                    EventList post = p.getValue(EventList.class);
                    //String name=post.getName();
                    items.add(post.getName());
                }

                doThis();
                settingOnClickListener();

                //s = s + post.getName();
                //System.out.println(p.getValue(members.class).toString());
                //System.out.println(post.getName() + " - " + post.getPassword());
            }

            //setText();
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
}

    public void doThis(){
        ArrayAdapter<String> itemsAdapter =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        mainListView.setAdapter(itemsAdapter);
    }

    public void settingOnClickListener(){
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selval = ((TextView) view).getText().toString();
                System.out.println(selval);
                Intent i = new Intent(getApplicationContext(), EventDetailsActivity.class);
                i.putExtra("selval", selval);
                i.putExtra("date", dating);
                startActivity(i);
            }
        });

    }


    public void goToNextDay(View view){
            items=new ArrayList<String>();
            Calendar c = Calendar.getInstance();
            System.out.println("Current time => " + c.getTime());
            SimpleDateFormat df = new SimpleDateFormat("dd");
            day=day+1;
            int formatted = day;
            //int formatted = (Integer.parseInt(df.format(c.getTime()))+count);
            df = new SimpleDateFormat("MMM-yyyy");
        String formattedDate="";
        if(formatted<10){
            formattedDate="0"+Integer.toString(formatted)+"-"+df.format(c.getTime());
        }
        else{
           formattedDate=Integer.toString(formatted)+"-"+df.format(c.getTime());
            }
            System.out.println("Current time => " + formattedDate);
            dating=formattedDate;

            tv.setText(dating);
            rootRef = new Firebase("https://brilliant-fire-6231.firebaseio.com/EventList/"+formattedDate);
            rootRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");
                    for (DataSnapshot p : snapshot.getChildren()) {
                        EventList post = p.getValue(EventList.class);
                        //String name=post.getName();
                        items.add(post.getName());
                    }
                    doThis();
                    settingOnClickListener();
                    count++;
                    //s = s + post.getName();
                    //System.out.println(p.getValue(members.class).toString());
                    //System.out.println(post.getName() + " - " + post.getPassword());
                }

                //setText();
                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    System.out.println("The read failed: " + firebaseError.getMessage());
                }
            });
    }

    public void goToSearch(View view){
        Intent i=new Intent(this,SearchActivity.class);
        startActivity(i);

    }

    public void goToPreviousDay(View view){
        day=day-1;
        items=new ArrayList<String>();
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("dd");
        int formatted = day;
        //int formatted = (Integer.parseInt(df.format(c.getTime()))+count);
        df = new SimpleDateFormat("MMM-yyyy");
        String formattedDate="0"+Integer.toString(formatted)+"-"+df.format(c.getTime());
        System.out.println("Current time => " + formattedDate);
        dating=formattedDate;
        tv.setText(dating);
        rootRef = new Firebase("https://brilliant-fire-6231.firebaseio.com/EventList/"+formattedDate);
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");
                for (DataSnapshot p : snapshot.getChildren()) {
                    EventList post = p.getValue(EventList.class);
                    //String name=post.getName();
                    items.add(post.getName());
                }
                doThis();
                settingOnClickListener();
                //s = s + post.getName();
                //System.out.println(p.getValue(members.class).toString());
                //System.out.println(post.getName() + " - " + post.getPassword());
            }

            //setText();
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

    }

    @Override
    public void onBackPressed() {
        //your code when back button pressed
        //nothing will happen
    }

    public void logOut(View view){
        session.logoutUser();
    }

}
