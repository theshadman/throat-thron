package com.example.shouvikshadman.dhakaevents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

public class SearchActivity extends AppCompatActivity {
    EditText ed;
    Button name,location,date;
    String text_to_search;
    Firebase rootRef;
    public ListView mainListView ;
    public ArrayList<String> items;
    public int day=0,dateInInt=0;
    String fullDate;
    public boolean check=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ed=(EditText)findViewById(R.id.editText10);
        name=(Button)findViewById(R.id.name);
        location=(Button)findViewById(R.id.location);
        date=(Button)findViewById(R.id.date);
        mainListView=(ListView)findViewById(R.id.listView3);
        items=new ArrayList<String>();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df0 = new SimpleDateFormat("dd");
        SimpleDateFormat df1 = new SimpleDateFormat("MMM-yyyy");
        fullDate = df1.format(c.getTime());
        String datePart= df0.format(c.getTime());
        dateInInt=Integer.parseInt(datePart);
        rootRef = new Firebase("https://brilliant-fire-6231.firebaseio.com/EventList/");
    }

    public void searchByName(View view){
        text_to_search=ed.getText().toString();
        int rupa=dateInInt;
        for(int j=0;j<10;j++){
            rupa+=j;
            if(rupa<10){
                String c1="0"+rupa+"-"+fullDate;
                System.out.println("Date to search in if " +c1);
                rootRef = new Firebase("https://brilliant-fire-6231.firebaseio.com/EventList/"+c1);
            }else{
                String c2=rupa+"-"+fullDate;
                System.out.println("Date to search in else " +c2);
                rootRef = new Firebase("https://brilliant-fire-6231.firebaseio.com/EventList/"+c2);
                //rootRef.child(c2);
            }
            rupa=dateInInt;
            inRootRefName();
        }
        //showTheDamnToast(check);
        check=false;
        items=new ArrayList<String>();
    }

    public void searchByDate(View view){
        text_to_search=ed.getText().toString();
        rootRef = new Firebase("https://brilliant-fire-6231.firebaseio.com/EventList/"+text_to_search);
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot p : snapshot.getChildren()) {
                    EventList post = p.getValue(EventList.class);
                    items.add(post.getName());
                    check=true;
                }
                doThis();
                rootRef.removeEventListener(this);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
        //showTheDamnToast(check);
        check=false;
        items=new ArrayList<String>();
    }

    public void searchByLocation(View view){
        text_to_search=ed.getText().toString();
        int rupa=dateInInt;
        for(int j=0;j<10;j++){
            rupa+=j;
            if(rupa<10){
                String c1="0"+rupa+"-"+fullDate;
                System.out.println("Date to search in if " +c1);
                rootRef = new Firebase("https://brilliant-fire-6231.firebaseio.com/EventList/"+c1);
            }else{
                String c2=rupa+"-"+fullDate;
                System.out.println("Date to search in else " +c2);
                rootRef = new Firebase("https://brilliant-fire-6231.firebaseio.com/EventList/"+c2);
                //rootRef.child(c2);
            }
            rupa=dateInInt;
            inRootRefLoc();
        }
        items=new ArrayList<String>();
    }


    public void doThis(){
        ArrayAdapter<String> itemsAdapter =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        mainListView.setAdapter(itemsAdapter);
    }

   /* public void showTheDamnToast(boolean s){
        if(!s){
            if(items.isEmpty()){
                Toast toast = Toast.makeText(getApplicationContext(), "No Events Found", Toast.LENGTH_SHORT);
                toast.show();
             }
        }
    }*/



    public void settingOnClickListener(){
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selval = ((TextView) view).getText().toString();
                Intent i = new Intent(getApplicationContext(), EventDetailsActivity.class);
                i.putExtra("selval", selval);
                startActivity(i);
            }
        });
    }

    public void inRootRefName(){
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot p : snapshot.getChildren()) {
                    System.out.println("Inside Search activity there are " + snapshot.getChildrenCount() + " posts");
                    EventList post = p.getValue(EventList.class);
                    String s = post.getName();
                    String ddd = post.getDate();
                    String arr[] = s.split(" ");
                    System.out.println("Found " + s + " converted to " + arr[0]);
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i].equalsIgnoreCase(text_to_search)) {
                            items.add(s + " happening on " + ddd);
                            check=true;
                            break;
                        }
                    }
                }
                doThis();
                //settingOnClickListener();
                rootRef.removeEventListener(this);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }

    public void inRootRefLoc(){
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot p : snapshot.getChildren()) {
                    System.out.println("Inside Search activity there are " + snapshot.getChildrenCount() + " posts");
                    EventList post = p.getValue(EventList.class);
                    String s=post.getLocation();
                    String n=post.getName();
                    String ddd=post.getDate();
                    String arr[]=s.split(" ");
                    System.out.println("Found " + s + " converted to "+arr[0]);
                    for(int i=0;i<arr.length;i++){
                        if(arr[i].equalsIgnoreCase(text_to_search)){
                            items.add(n+" happening on "+ddd);
                            check=true;
                            break;
                        }
                    }
                }
                doThis();
                //settingOnClickListener();
                rootRef.removeEventListener(this);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }

}
