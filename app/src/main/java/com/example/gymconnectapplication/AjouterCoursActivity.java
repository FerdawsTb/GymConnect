package com.example.gymconnectapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AjouterCoursActivity extends BaseDrawerActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ajouter_cours);
        setupDrawer();
    }
}
