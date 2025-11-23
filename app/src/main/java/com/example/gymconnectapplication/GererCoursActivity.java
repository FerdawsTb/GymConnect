package com.example.gymconnectapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class GererCoursActivity extends BaseDrawerActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gerer_cours);
        setupDrawer();
    }
    public void goToAjouterCours(View view) {
        Intent intent = new Intent(this, AjouterCoursActivity.class);
        startActivity(intent);
    }
}
