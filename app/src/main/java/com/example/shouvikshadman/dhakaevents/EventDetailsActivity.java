package com.example.shouvikshadman.dhakaevents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EventDetailsActivity extends AppCompatActivity {
    TextView tv,tv2;
    Firebase rootRef;
    ListView mainListView;
    public String nameCheck;
    public String location;
    public ArrayList<String> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Firebase.setAndroidContext(this);
        //Intent intent=getIntent();
        //tv=(TextView)findViewById(R.id.textView15);
       // tv2=(TextView)findViewById(R.id.textView16);
        Bundle hello=getIntent().getExtras();
        nameCheck=hello.getString("selval");
        //tv.setText(nameCheck);
        //tv2.setText(hello.getString("date"));
        mainListView=(ListView)findViewById(R.id.listView2);
        items=new ArrayList<String>();
        rootRef = new Firebase("https://brilliant-fire-6231.firebaseio.com/EventList/"+hello.getString("date"));
            rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");
                for (DataSnapshot p : snapshot.getChildren()) {
                    EventList post = p.getValue(EventList.class);
                    if(post.getName().equals(nameCheck)){
                        items.add(post.getName());
                        items.add(post.getDate());
                        items.add(post.getDescription());
                        items.add(post.getLocation());
                        items.add(post.getTime());
                        location=post.getLocation();
                    }
                }
                doThis();
                rootRef.removeEventListener(this);

                //settingOnClickListener();

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

    public void showInMap(View view){
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("google.navigation:q=" + location));
        startActivity(intent);
    }
}
