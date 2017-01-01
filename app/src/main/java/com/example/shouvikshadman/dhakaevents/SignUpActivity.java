package com.example.shouvikshadman.dhakaevents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    EditText ed1,ed2;
    Button bt1;
    String username,password;
    Firebase rootRef;
    boolean test=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Firebase.setAndroidContext(this);
        rootRef = new Firebase("https://brilliant-fire-6231.firebaseio.com/");
        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);
        bt1=(Button)findViewById(R.id.button);
    }

    public void admitInformation(View view){
        username=ed1.getText().toString();
        password=ed2.getText().toString();
        rootRef = new Firebase("https://brilliant-fire-6231.firebaseio.com/members");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");
                for (DataSnapshot p: snapshot.getChildren()) {
                    members post = p.getValue(members.class);
                    String name=post.getName();
                    if(name.equals(username)){
                        Toast toast = Toast.makeText(getApplicationContext(), "This username is taken, " +
                                "please choose a different username", Toast.LENGTH_SHORT);
                        toast.show();
                        test=false;
                    }
                }
                if(test){
                    admitThis();
                }
                test=true;
                rootRef.removeEventListener(this);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }

    public void admitThis(){
        Firebase myThing= rootRef;
        Firebase pushdata=myThing.push();
        Map<String, String> post1 = new HashMap<String, String>();
        post1.put("name", username);
        post1.put("password", password);
        pushdata.setValue(post1);
        System.out.println(pushdata.getKey().toString());
        Toast toast = Toast.makeText(getApplicationContext(), "Success in Creating a new account!!!", Toast.LENGTH_SHORT);
        toast.show();
        Intent i=new Intent(this,EventListActivity.class);
        startActivity(i);
    }


}
