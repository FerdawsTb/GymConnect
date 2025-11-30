package com.example.gymconnectapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import android.widget.EditText;

public class ResetPasswordActivity extends AppCompatActivity {

    TextInputLayout verificationCodeLayout, passwordLayout, confirmPasswordLayout;
    EditText editTextVerificationCode, editTextPassword, editTextConfirmPassword;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        email = getIntent().getStringExtra("EMAIL");

        verificationCodeLayout = findViewById(R.id.verificationCodeLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
        confirmPasswordLayout = findViewById(R.id.confirmPasswordLayout);

        editTextVerificationCode = findViewById(R.id.editTextVerificationCode);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
    }

    public void onResetPasswordClick(View view) {
        if (!validateForm()) {
            return;
        }

        String verificationCode = editTextVerificationCode.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Simuler la vérification du code et la réinitialisation
        // En production, cela appellerait un backend/Firebase
        if (verificationCode.isEmpty() || verificationCode.length() < 4) {
            verificationCodeLayout.setError("Code invalide");
            return;
        }

        Toast.makeText(this, "Mot de passe réinitialisé avec succès!", Toast.LENGTH_LONG).show();

        // Retour à la page de login
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean validateForm() {
        boolean isValid = true;

        String verificationCode = editTextVerificationCode.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        verificationCodeLayout.setError(null);
        passwordLayout.setError(null);
        confirmPasswordLayout.setError(null);

        if (verificationCode.isEmpty()) {
            verificationCodeLayout.setError("Code de vérification obligatoire");
            isValid = false;
        }

        if (password.isEmpty()) {
            passwordLayout.setError("Mot de passe obligatoire");
            isValid = false;
        } else if (password.length() < 8) {
            passwordLayout.setError("Min 8 caractères");
            isValid = false;
        }

        if (confirmPassword.isEmpty()) {
            confirmPasswordLayout.setError("Confirmation obligatoire");
            isValid = false;
        } else if (!password.equals(confirmPassword)) {
            confirmPasswordLayout.setError("Les mots de passe ne correspondent pas");
            isValid = false;
        }

        return isValid;
    }

    public void onBackClick(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
