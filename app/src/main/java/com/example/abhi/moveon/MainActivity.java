package com.example.abhi.moveon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void riderLogin(){
        startActivity(new Intent(this,RiderLoginActivity.class));
    }


    public void mechanicLogin(){
        startActivity(new Intent(this,MechanicLoginActivity.class));
    }
}
