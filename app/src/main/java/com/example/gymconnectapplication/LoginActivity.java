package com.example.gymconnectapplication;

import android.content.Intent;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
    }
        public void onSignInClick(View view) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }

        public void goToSignUp(View view) {
            // Navigation vers SignUpActivity
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
    }
    public void goToClientProfile (View view) {
        // Navigation vers activity_client_profile
        Intent intent = new Intent(this, ClientProfileActivity.class);
        startActivity(intent);

    }
}