package com.example.smart_irrigation_system;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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

    TextView txttempt,txtsoil,txtdistance,txthumidity,txtdistresult,tvtempdate,tvhumiditydate,tvsoildate;
    LinearLayout image;
    private String humidity,temperature,soil,distance;
    private FirebaseAuth mAuth;
    CardView CvRefresh;
    private DatabaseReference mDatabase;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        txthumidity=findViewById(R.id.tvhumidity);
        txttempt=findViewById(R.id.tvtemp);
        txtsoil=findViewById(R.id.tvsoil);
        txtdistance=findViewById(R.id.tvdist);
        txtdistresult=findViewById(R.id.tvdistresult);
        image=findViewById(R.id.distimage);
        CvRefresh=findViewById(R.id.CvRefresh);
        tvtempdate=findViewById(R.id.Tvtemperaturedate);
        tvhumiditydate=findViewById(R.id.Tvhumiditydate);
        tvsoildate=findViewById(R.id.Tvsoilmoisturedate);

        CvRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });


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
                txtdistance.setText("Water Level: "+distance+"cm Down");

                int dis=Integer.parseInt(distance);

                if (dis<=5)
                {
                    txtdistresult.setText("Tank is full");
                    image.setBackgroundResource(R.drawable.green);
                }
                else if (dis<=10 && dis>=5)
                {
                    txtdistresult.setText("Tank is half Empty");
                    image.setBackgroundResource(R.drawable.yellow);
                }
                else
                {
                    txtdistresult.setText("Tank is Empty");
                    image.setBackgroundResource(R.drawable.red);
                }
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

    private void refresh() {
        Intent intent=new Intent(MainDashboardActivity.this,MainDashboardActivity.class);
        startActivity(intent);
        finish();
    }


}


