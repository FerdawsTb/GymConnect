package com.example.gymconnectapplication;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
public class AdminGererAbonnementActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_gerer_abonnement);
    }
    public void goToAjouterAbonnement(View view) {
        Intent intent = new Intent(this, AjouterAbonnementActivity.class);
        startActivity(intent);
    }
}
