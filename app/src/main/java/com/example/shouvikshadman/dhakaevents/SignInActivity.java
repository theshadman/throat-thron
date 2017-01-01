package com.example.shouvikshadman.dhakaevents;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class SignInActivity extends AppCompatActivity {
    EditText ed1,ed2;
    TextView tv1,tv2;
    Button bt;
    Firebase rootRef;
    SessionManager session;
    private PopupWindow pw;
    private View popupView;
    private LayoutInflater inflater;

    //Context context = getActivity();
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Firebase.setAndroidContext(this);
        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);
        bt=(Button)findViewById(R.id.button);
        tv1=(TextView)findViewById(R.id.textView3);
        tv2=(TextView)findViewById(R.id.textView4);
        session = new SessionManager(getApplicationContext());
    }

    @Override
    public void onBackPressed() {
        //your code when back button pressed
        //nothing will happen
        Intent i=new Intent(this,SigningActivity.class);
        startActivity(i);
    }

    public void retrieveInformation(View view){
        final String givenName=ed1.getText().toString();
        final String givenPass=ed2.getText().toString();

        rootRef = new Firebase("https://brilliant-fire-6231.firebaseio.com/members");
        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");
                for (DataSnapshot p: snapshot.getChildren()) {
                    members post = p.getValue(members.class);
                    String name=post.getName();
                    String pass= post.getPassword();
                    if(name.equals(givenName) && pass.equals(givenPass)){
                        putIn(name);
                        Toast toast = Toast.makeText(getApplicationContext(), "Success Logging in", Toast.LENGTH_SHORT);
                        toast.show();
                        if(name.equals("shouvik") && pass.equals("shou433"))
                            goToAdmin();
                        else
                            goToEventList();
                        increment();
                        break;
                    }
                }
                if(nothingFound()){
                    Toast toast = Toast.makeText(getApplicationContext(), "Log in failed", Toast.LENGTH_SHORT);
                    toast.show();
                }
                rootRef.removeEventListener(this);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }

    public void increment(){
        i++;
    }
    public boolean nothingFound(){
        if(i>0){
            i=0;
            return false;
        }else
            return true;
    }
    public void goToEventList(){
        Intent i=new Intent(this,EventListActivity.class);
        startActivity(i);
    }
    public void goToAdmin(){
        Intent i=new Intent(this,AdminPage.class);
        startActivity(i);
    }

    public void putIn(String s){
        session.createLoginSession(s);
    }



    @Override
    public void onPause(){
        super.onPause();
    }

}
