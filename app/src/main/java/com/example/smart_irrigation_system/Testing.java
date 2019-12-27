package com.example.smart_irrigation_system;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Testing extends AppCompatActivity {

    private TextView mvalueView;
    private EditText ettest,ettestadd;
    private Button btnadd;
    private DatabaseReference mRef;
    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);


        mvalueView= findViewById(R.id.tvtest);
        ettest=findViewById(R.id.ettest);
        ettestadd=findViewById(R.id.ettestadd);
        btnadd=findViewById(R.id.btnadd);


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 myapp();
            }
        });

        mRef= FirebaseDatabase.getInstance().getReference();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myReft=database.getReference("soilmoisture");
        myReft.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                status=dataSnapshot.getValue().toString();
                mvalueView.setText(status);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }

    private void myapp() {
                        FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference myReft=database.getReference("LED_STATUS");
                myReft.setValue(1);
    }
}
