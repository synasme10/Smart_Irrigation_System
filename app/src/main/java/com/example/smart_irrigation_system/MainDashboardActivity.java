package com.example.smart_irrigation_system;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainDashboardActivity extends AppCompatActivity {

    TextView Tvhumidity,Tvtemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        Tvhumidity=findViewById(R.id.humidity);

    }
}
