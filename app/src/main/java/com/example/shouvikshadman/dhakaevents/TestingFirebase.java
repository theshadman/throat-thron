package com.example.shouvikshadman.dhakaevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestingFirebase extends AppCompatActivity {
    Firebase rootRef;
    Button bt1,bt2;
    TextView tv1;
    EditText ed1,ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_firebase);
        Firebase.setAndroidContext(this);
        rootRef = new Firebase("https://brilliant-fire-6231.firebaseio.com/");
        bt1=(Button)findViewById(R.id.button3);
        bt2=(Button)findViewById(R.id.button4);
        tv1=(TextView)findViewById(R.id.textView14);
        ed1=(EditText)findViewById(R.id.editText8);
        ed2=(EditText)findViewById(R.id.editText9);
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());
        System.out.println("Current time => " + formattedDate);
        tv1.setText(formattedDate);
    }

    public void sendData(View view){
        Firebase testRef=rootRef.child("test").child("30-Mar-2016");
        String a=ed1.getText().toString();
        String b=ed2.getText().toString();
        testRef.child(a).setValue(b);
    }


    public void getData(View view){

    }
}
