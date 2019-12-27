package com.example.smart_irrigation_system;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WateringControlActivity extends AppCompatActivity {

    private ImageView Ivturnon,Ivturnoff,Ivfield;

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watering_control);

        Ivturnoff=findViewById(R.id.turnoffpump);
        Ivturnon=findViewById(R.id.turnonpump);
        Ivfield=findViewById(R.id.field);

        Ivturnoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnoffwaterpump();
            }
        });

        Ivturnon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnonwaterpump();
            }
        });


    }

    private void turnonwaterpump() {
        DatabaseReference myReft=database.getReference("pump_status");
        myReft.setValue(1);
        Ivfield.setBackgroundResource(R.drawable.green);
    }

    private void turnoffwaterpump() {

        DatabaseReference myReft=database.getReference("pump_status");
        myReft.setValue(0);
        Ivfield.setBackgroundResource(R.drawable.yellow);
  }
}
