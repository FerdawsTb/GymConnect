package com.example.gymconnectapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ReserverCoursActivity extends BaseDrawerActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserver_cours);
        setupDrawer();
    }
}
