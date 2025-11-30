package com.example.gymconnectapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import android.widget.EditText;

public class ForgotPasswordActivity extends AppCompatActivity {

    TextInputLayout emailLayout;
    EditText editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailLayout = findViewById(R.id.emailLayout);
        editTextEmail = findViewById(R.id.editTextEmail);
    }

    public void onSendResetCodeClick(View view) {
        if (!validateEmail()) {
            return;
        }

        String email = editTextEmail.getText().toString().trim();
        
        // Simuler l'envoi du code
        // En production, cela appellerait un backend/Firebase
        Toast.makeText(this, "Code de réinitialisation envoyé à " + email, Toast.LENGTH_LONG).show();

        // Passer à la page de réinitialisation du mot de passe
        Intent intent = new Intent(this, ResetPasswordActivity.class);
        intent.putExtra("EMAIL", email);
        startActivity(intent);
        finish();
    }

    private boolean validateEmail() {
        boolean isValid = true;

        String email = editTextEmail.getText().toString().trim();
        emailLayout.setError(null);

        if (email.isEmpty()) {
            emailLayout.setError("Email obligatoire");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.setError("Format email invalide");
            isValid = false;
        }

        return isValid;
    }

    public void onBackClick(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
