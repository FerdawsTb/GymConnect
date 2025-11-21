package com.example.gymconnectapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
    }
        public void onSignUpClick(View view) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish(); // Ferme SignUpActivity
        }
        public void goToLogin(View view) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
    }
    public void goToAdminHome (View view) {
        // Navigation vers activity_client_profile
        Intent intent = new Intent(this, AdminHomeActivity.class);
        startActivity(intent);
    }
}