package com.example.gymconnectapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout emailLayout, passwordLayout;
    EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailLayout = findViewById(R.id.emailLayout);
        passwordLayout = findViewById(R.id.passwordLayout);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
    }

    // 🔥 Une seule méthode pour le bouton !
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

        // Optionnel : envoyer le rôle
        intent.putExtra("USER_ROLE", userRole);

        startActivity(intent);
        finish();
    }

    private boolean validateForm() {
        boolean isValid = true;

        String email = editTextEmail.getText().toString().trim();// supprimer les espaces avant et aprés le email
        String password = editTextPassword.getText().toString().trim();
        // effacer les anciens erreurs
        emailLayout.setError(null);
        passwordLayout.setError(null);

        if (email.isEmpty()) {
            emailLayout.setError("Email obligatoire");
            isValid = false;
            //vérifie si l’email respecte le format : exemple@domaine.com
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.setError("Format email invalide");
            isValid = false;
        }

        if (password.isEmpty()) {
            passwordLayout.setError("Mot de passe obligatoire");
            isValid = false;
        } else if (password.length() < 8) {
            passwordLayout.setError("Min 8 caractères");
            isValid = false;
        }

        return isValid;
    }

    public void goToSignUp(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }
}
