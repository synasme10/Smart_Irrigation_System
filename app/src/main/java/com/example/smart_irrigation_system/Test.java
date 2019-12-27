package com.example.smart_irrigation_system;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Test extends AppCompatActivity {

    private TextView tvDistance;
private Button btnSave;
    private String distance;
    private DatabaseReference mDatabaseReference;

    private Model model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        btnSave = findViewById(R.id.btnSave);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Model model = dataSnapshot.getValue(Model.class);
                distance = model.getHumidity();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(Test.this, "tera bauu"+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        tvDistance = findViewById(R.id.tvdistance);
        tvDistance.setText(distance);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setDistance("1213");
                model.setHumidity("123123");
                model.setTemperature("1231231");
                mDatabaseReference.push().setValue(model);
                Toast.makeText(Test.this, "Saved", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
