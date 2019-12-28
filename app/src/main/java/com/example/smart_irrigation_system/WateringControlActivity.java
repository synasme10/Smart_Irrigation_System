package com.example.smart_irrigation_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WateringControlActivity extends AppCompatActivity {

    private ImageView Ivturnon,Ivturnoff,Ivfield,Ivwatering,btnback;
    private TextView tvwater;


    FirebaseDatabase database=FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watering_control);

        Ivturnoff=findViewById(R.id.turnoffpump);
        Ivturnon=findViewById(R.id.turnonpump);
        Ivfield=findViewById(R.id.field);
        tvwater=findViewById(R.id.tvwatering);
        Ivwatering=findViewById(R.id.watering);
        btnback=findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home();
            }
        });

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

    private void home() {
        Intent intent=new Intent(WateringControlActivity.this,MainDashboardActivity.class);
        startActivity(intent);
    }

    private void turnonwaterpump() {
        DatabaseReference myReft=database.getReference("pump_status");
        myReft.setValue(1);
        Ivfield.setVisibility(View.GONE);
        tvwater.setText("");
        Ivwatering.setVisibility(View.INVISIBLE);

        Ivfield.setBackgroundResource(R.drawable.sprinkler);
        Ivfield.setVisibility(View.VISIBLE);

    }

    private void turnoffwaterpump() {

        DatabaseReference myReft=database.getReference("pump_status");
        myReft.setValue(0);
        Ivfield.setBackgroundResource(R.drawable.grass1);
        Ivwatering.setVisibility(View.VISIBLE);
        tvwater.setText("Turn On for Watering");
  }
}
