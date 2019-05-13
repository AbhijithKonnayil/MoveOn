package com.example.abhi.moveon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnRider,btnMechanic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMechanic = (Button)findViewById(R.id.mec_btn);
        btnRider = (Button)findViewById(R.id.rider_btn);

        btnRider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RiderLoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        btnMechanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MechanicLoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }

}
