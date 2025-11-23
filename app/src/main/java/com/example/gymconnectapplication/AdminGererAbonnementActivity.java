package com.example.gymconnectapplication;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class AdminGererAbonnementActivity extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_gerer_abonnement);

        setupDrawer();
    }

    public void goToAjouterAbonnement(View view) {
        Intent intent = new Intent(this, AjouterAbonnementActivity.class);
        startActivity(intent);
    }
}