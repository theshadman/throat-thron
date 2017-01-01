package com.example.shouvikshadman.dhakaevents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class SigningActivity extends AppCompatActivity {
    Button sI;
    Button sU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signing);
        sI=(Button)findViewById(R.id.signIn);
        sU=(Button)findViewById(R.id.signUp);

    }

    public void SignIn(View view){
        Intent i=new Intent(this,SignInActivity.class);
        startActivity(i);
    }

    public void SignUp(View view){
        Intent i=new Intent(this,SignUpActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        //your code when back button pressed
        //nothing will happen
    }
}
