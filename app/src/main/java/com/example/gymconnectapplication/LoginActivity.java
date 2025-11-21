package com.example.gymconnectapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onSignInClick(View view) {
        String userRole = "ADMIN"; // Changez en "COACH" ou "CLIENT" pour tester

        // Selon le rôle, ouvrir la bonne page d'accueil
        Intent intent;

        if (userRole.equals("ADMIN")) {
            intent = new Intent(this, AdminHomeActivity.class);
        } else if (userRole.equals("COACH")) {
            intent = new Intent(this, CoachHomeActivity.class);
        } else {
            intent = new Intent(this, HomeActivity.class);
        }

        // Passer le rôle pour que le menu soit disponible
        intent.putExtra("USER_ROLE", userRole);
        startActivity(intent);
        finish();
    }

    public void goToSignUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void goToAdminHome(View view) {
        Intent intent = new Intent(this, AdminHomeActivity.class);
        startActivity(intent);
    }
}