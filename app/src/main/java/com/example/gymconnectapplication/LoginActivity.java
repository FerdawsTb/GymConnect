package com.example.gymconnectapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

// --- Imports de tes classes Retrofit (Vérifie qu'ils sont corrects) ---
import com.example.gymconnectapplication.model.LoginResponse;
import com.example.gymconnectapplication.model.LoginRequest;
import com.example.gymconnectapplication.network.RetrofitClient;
import com.example.gymconnectapplication.network.GymApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout emailLayout, passwordLayout;
    EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Liaison XML
        emailLayout = findViewById(R.id.emailLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
    }

<<<<<<< HEAD
    public void onSignInClick(View view) {

        if (!validateForm()) {
            return; // Stop si les champs ne sont pas valides
        }

        String userRole = "ADMIN"; // "COACH" ou "CLIENT"

        Intent intent;

        switch (userRole) {
            case "ADMIN":
                intent = new Intent(this, AdminHomeActivity.class);
                break;
            case "COACH":
                intent = new Intent(this, CoachHomeActivity.class);
                break;
            default:
                intent = new Intent(this, HomeActivity.class);
                break;
        }


        intent.putExtra("USER_ROLE", userRole);

        startActivity(intent);
=======
    public void goToPasswordForgot(View view) {
        startActivity(new Intent(this, ForgotPasswordActivity.class));
>>>>>>> 6f5ae1d ( liaison avec BD)
        finish();
    }

    // Méthode appelée par le bouton "Se connecter"
    public void onSignInClick(View view) {
        if (!validateForm()) {
            return;
        }

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Lancer la connexion
        performLogin(email, password);
    }

    private void performLogin(String email, String password) {
        // Préparation de la requête (Android envoie le mot de passe tel quel)
        // Spring Boot va le comparer avec la version chiffrée en BD
        LoginRequest request = new LoginRequest(email, password);

        GymApiService apiService = RetrofitClient.getApiService();
        Call<LoginResponse> call = apiService.loginClient(request);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    //  ---MRGL  ---
                    String token = response.body().getToken();

                    // 1. Sauvegarder le token (pour l'utiliser dans le profil plus tard)
                    SharedPreferences preferences = getSharedPreferences("GymAppPrefs", MODE_PRIVATE);
                    preferences.edit().putString("auth_token", token).apply();

                    Toast.makeText(LoginActivity.this, "Bienvenue !", Toast.LENGTH_SHORT).show();

                    // 2. Redirection vers ClientProfileActivity comme demandé
                    Intent intent = new Intent(LoginActivity.this, ClientProfileActivity.class);
                    startActivity(intent);
                    finish(); // Empêche de revenir au login avec le bouton retour

                } else {
                    // --- ghalta ---
                    passwordLayout.setError("Email ou mot de passe incorrect");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // --- ERREUR RÉSEAU ---
                Toast.makeText(LoginActivity.this, "Erreur serveur : " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean validateForm() {
        boolean isValid = true;
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        emailLayout.setError(null);
        passwordLayout.setError(null);

        if (email.isEmpty()) {
            emailLayout.setError("Email obligatoire");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.setError("Format email invalide");
            isValid = false;
        }

        if (password.isEmpty()) {
            passwordLayout.setError("Mot de passe obligatoire");
            isValid = false;
        }
        // Tu peux remettre < 8 si tu veux être strict, < 3 est bien pour tester
        else if (password.length() < 3) {
            passwordLayout.setError("Mot de passe trop court");
            isValid = false;
        }

        return isValid;
    }

    public void goToSignUp(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }
}