package com.example.gymconnectapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gymconnectapplication.network.GymApiService;
import com.example.gymconnectapplication.network.RegisterRequest;
import com.example.gymconnectapplication.network.RegisterResponse;
import com.example.gymconnectapplication.network.RetrofitClient;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    TextInputLayout firstNameLayout, lastNameLayout, emailLayout, phoneLayout,
            genderLayout, ageLayout, weightLayout, heightLayout, passwordLayout, confirmPasswordLayout;

    EditText etFirstName, etLastName, etEmail, etPhone, etAge, etWeight, etHeight, etPassword, etConfirmPassword;
    AutoCompleteTextView actvGender;
    CheckBox cbTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialisation des Layouts
        firstNameLayout = findViewById(R.id.tilFirstName);
        lastNameLayout = findViewById(R.id.tilLastName);
        emailLayout = findViewById(R.id.tilEmail);
        phoneLayout = findViewById(R.id.tilPhone);
        genderLayout = findViewById(R.id.tilGender);
        ageLayout = findViewById(R.id.tilAge);
        weightLayout = findViewById(R.id.tilWeight); // Nouveau
        heightLayout = findViewById(R.id.tilHeight); // Nouveau
        passwordLayout = findViewById(R.id.tilPassword);
        confirmPasswordLayout = findViewById(R.id.tilConfirmPassword);

        // Initialisation des EditTexts
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        actvGender = findViewById(R.id.actvGender);
        etAge = findViewById(R.id.etAge);
        etWeight = findViewById(R.id.etWeight); // Nouveau
        etHeight = findViewById(R.id.etHeight); // Nouveau
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);

        cbTerms = findViewById(R.id.cbTerms);

        // Configuration du menu déroulant (Gender)
        // Assure-toi d'avoir <string-array name="genders"> dans res/values/strings.xml
        // Sinon utilise cette liste temporaire :
        String[] genders = {"Homme", "Femme", "Autre"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                genders
        );
        actvGender.setAdapter(genderAdapter);
    }

    public void onSignUpClick(View view) {
        if (!validateForm()) return;

        // Récupération des données finales
        String nom = etLastName.getText().toString().trim(); // Nom de famille
        String prenom = etFirstName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String motDePasse = etPassword.getText().toString().trim();
        String telephone = etPhone.getText().toString().trim();
        String sexe = actvGender.getText().toString().trim();
        Integer age = Integer.parseInt(etAge.getText().toString().trim());
        Double poids = Double.parseDouble(etWeight.getText().toString().trim());
        Double taille = Double.parseDouble(etHeight.getText().toString().trim());

        // Création de l'objet Request
        RegisterRequest request = new RegisterRequest(
                nom, prenom, email, motDePasse, telephone, sexe, age, poids, taille
        );

        // Appel API
        GymApiService apiService = RetrofitClient.getApiService();
        Call<RegisterResponse> call = apiService.registerClient(request);

        // Afficher un chargement ici si tu veux (ProgressBar)

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Succès (Code 201)
                    Toast.makeText(SignUpActivity.this, "Inscription réussie : " + response.body().getMessage(), Toast.LENGTH_LONG).show();

                    // Rediriger vers Login
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class); // Assure-toi d'avoir LoginActivity
                    startActivity(intent);
                    finish();
                } else {
                    // Erreur (Code 400, 409, 500)
                    try {
                        String errorBody = response.errorBody().string();
                        Toast.makeText(SignUpActivity.this, "Erreur: " + errorBody, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(SignUpActivity.this, "Erreur inconnue lors de l'inscription", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                // Erreur réseau (Pas d'internet, serveur éteint)
                Toast.makeText(SignUpActivity.this, "Erreur réseau: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("API_ERROR", t.getMessage());
            }
        });
    }

    public void goToLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class)); // Assure-toi d'avoir LoginActivity
        finish();
    }

    private boolean validateForm() {
        boolean isValid = true;

        // Reset errors
        firstNameLayout.setError(null);
        lastNameLayout.setError(null);
        emailLayout.setError(null);
        weightLayout.setError(null);
        heightLayout.setError(null);
        // ... reset others ...

        String firstName = etFirstName.getText().toString().trim();
        if (firstName.isEmpty()) { firstNameLayout.setError("Requis"); isValid = false; }

        String lastName = etLastName.getText().toString().trim();
        if (lastName.isEmpty()) { lastNameLayout.setError("Requis"); isValid = false; }

        String email = etEmail.getText().toString().trim();
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.setError("Email invalide"); isValid = false;
        }

        String phone = etPhone.getText().toString().trim();
        if (phone.isEmpty()) { phoneLayout.setError("Requis"); isValid = false; }

        String gender = actvGender.getText().toString().trim();
        if (gender.isEmpty()) { genderLayout.setError("Requis"); isValid = false; }

        String ageStr = etAge.getText().toString().trim();
        if (ageStr.isEmpty()) { ageLayout.setError("Requis"); isValid = false; }

        // Validation Poids
        String weightStr = etWeight.getText().toString().trim();
        if (weightStr.isEmpty()) { weightLayout.setError("Requis"); isValid = false; }

        // Validation Taille
        String heightStr = etHeight.getText().toString().trim();
        if (heightStr.isEmpty()) { heightLayout.setError("Requis"); isValid = false; }

        String password = etPassword.getText().toString().trim();
        if (password.length() < 8) { passwordLayout.setError("Min 8 caractères"); isValid = false; }

        String confirmPassword = etConfirmPassword.getText().toString().trim();
        if (!confirmPassword.equals(password)) { confirmPasswordLayout.setError("Les mots de passe ne correspondent pas"); isValid = false; }

        if (!cbTerms.isChecked()) {
            cbTerms.setError("Acceptez les conditions");
            isValid = false;
        }

        return isValid;
    }
}