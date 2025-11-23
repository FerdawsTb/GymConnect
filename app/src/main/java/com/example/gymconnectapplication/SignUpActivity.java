package com.example.gymconnectapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {

    TextInputLayout firstNameLayout, lastNameLayout, emailLayout, phoneLayout,
            genderLayout, ageLayout, passwordLayout, confirmPasswordLayout;

    EditText etFirstName, etLastName, etEmail, etPhone, etAge, etPassword, etConfirmPassword;
    AutoCompleteTextView actvGender;
    CheckBox cbTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Récupération des composants
        firstNameLayout = findViewById(R.id.tilFirstName);
        lastNameLayout = findViewById(R.id.tilLastName);
        emailLayout = findViewById(R.id.tilEmail);
        phoneLayout = findViewById(R.id.tilPhone);
        genderLayout = findViewById(R.id.tilGender);
        ageLayout = findViewById(R.id.tilAge);
        passwordLayout = findViewById(R.id.tilPassword);
        confirmPasswordLayout = findViewById(R.id.tilConfirmPassword);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        actvGender = findViewById(R.id.actvGender);
        etAge = findViewById(R.id.etAge);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);

        cbTerms = findViewById(R.id.cbTerms);

        //le menu déroulant Gender
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                getResources().getStringArray(R.array.genders)
        );

        actvGender.setAdapter(genderAdapter);
    }

    public void onSignUpClick(View view) {
        if (!validateForm()) return;

        // TEMP : Navigation (plus tard tu ajouteras la vraie logique)
        Intent intent = new Intent(this, AdminHomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private boolean validateForm() {
        boolean isValid = true;

        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String gender = actvGender.getText().toString().trim();
        String age = etAge.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        // Reset errors
        firstNameLayout.setError(null);
        lastNameLayout.setError(null);
        emailLayout.setError(null);
        phoneLayout.setError(null);
        genderLayout.setError(null);
        ageLayout.setError(null);
        passwordLayout.setError(null);
        confirmPasswordLayout.setError(null);

        // First name
        if (firstName.isEmpty()) {
            firstNameLayout.setError("Required");
            isValid = false;
        }

        // Last name
        if (lastName.isEmpty()) {
            lastNameLayout.setError("Required");
            isValid = false;
        }

        // Email
        if (email.isEmpty()) {
            emailLayout.setError("Required");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.setError("Invalid email");
            isValid = false;
        }

        // Phone
        if (phone.isEmpty()) {
            phoneLayout.setError("Required");
            isValid = false;
        } else if (phone.length() != 8) {
            phoneLayout.setError("Invalid phone number");
            isValid = false;
        }

        // Gender
        if (gender.isEmpty()) {
            genderLayout.setError("Required");
            isValid = false;
        }

        // Age
        if (age.isEmpty()) {
            ageLayout.setError("Required");
            isValid = false;
        } else if (Integer.parseInt(age) < 12) {
            ageLayout.setError("Too young");
            isValid = false;
        }

        // Password
        if (password.isEmpty()) {
            passwordLayout.setError("Required");
            isValid = false;
        } else if (password.length() < 8) {
            passwordLayout.setError("At least 8 characters");
            isValid = false;
        }

        // Confirm password
        if (!confirmPassword.equals(password)) {
            confirmPasswordLayout.setError("Passwords do not match");
            isValid = false;
        }

        // Terms
        if (!cbTerms.isChecked()) {
            cbTerms.setError("You must accept the terms");
            isValid = false;
        } else {
            cbTerms.setError(null);
        }

        return isValid;
    }
}
