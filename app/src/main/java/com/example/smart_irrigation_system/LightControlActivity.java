package com.example.smart_irrigation_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LightControlActivity extends AppCompatActivity {

    private ImageView Ivturnonlight, Ivturnofflight, Ivbulb, btnbacklight;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_control);


        Ivturnonlight=findViewById(R.id.turnonlight);
        Ivturnofflight=findViewById(R.id.turnofflight);
        Ivbulb=findViewById(R.id.bulb);
        btnbacklight=findViewById(R.id.btnlightback);

        btnbacklight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home();
            }
        });

        Ivturnofflight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnofflight();
            }
        });

        Ivturnonlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnonlight();
            }
        });
    }

    private void turnonlight() {
        DatabaseReference myReft=database.getReference("light_status");
        myReft.setValue(1);
        Ivbulb.setBackgroundResource(R.drawable.turnonlight);
    }

    private void turnofflight() {
        DatabaseReference myReft=database.getReference("light_status");
        myReft.setValue(0);
        Ivbulb.setBackgroundResource(R.drawable.turnofflight);
    }

    private void home() {
        Intent intent=new Intent(LightControlActivity.this,MainDashboardActivity.class);
        startActivity(intent);
    }

    
}
