package com.example.smart_irrigation_system;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainDashboardActivity extends AppCompatActivity {

    TextView txttempt,txtsoil,txtdistance,txthumidity;
    private String humidity,temperature,soil,distance;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        txthumidity=findViewById(R.id.tvhumidity);
        txttempt=findViewById(R.id.tvtemp);
        txtsoil=findViewById(R.id.tvsoil);
        txtdistance=findViewById(R.id.tvdist);


        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myReft=database.getReference("soilmoisture");
        myReft.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                soil=dataSnapshot.getValue().toString();
                txtsoil.setText(soil);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        DatabaseReference myReft1=database.getReference("distance");
        myReft1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                distance=dataSnapshot.getValue().toString();
                txtdistance.setText(distance);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        DatabaseReference myReft2=database.getReference("humidity");
        myReft2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                humidity=dataSnapshot.getValue().toString();
                txthumidity.setText(humidity);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        DatabaseReference myReft3=database.getReference("temperature");
        myReft3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                temperature=dataSnapshot.getValue().toString();
                txttempt.setText(temperature);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }



    }


