package com.example.gymconnectapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public abstract class BaseDrawerActivity extends AppCompatActivity {

    protected DrawerLayout drawerLayout;
    protected NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Gestion du bouton retour physique pour fermer le menu
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        });
    }

<<<<<<< HEAD
=======
    // Méthode à appeler dans le onCreate des enfants
>>>>>>> 6f5ae1d ( liaison avec BD)
    protected void setupDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    handleNavigationItemSelected(item);
                    return true;
                }
            });
        }
    }

<<<<<<< HEAD

=======
    // Appelée par le bouton XML (android:onClick="openDrawer")
>>>>>>> 6f5ae1d ( liaison avec BD)
    public void openDrawer(View view) {
        if (drawerLayout != null) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    // Gestion centralisée des clics du menu
    protected void handleNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        // --- SECTION ADMIN ---
        if (id == R.id.nav_admin_home) {
            startActivity(new Intent(this, AdminHomeActivity.class));
        } else if (id == R.id.nav_gerer_abonnements) {
            startActivity(new Intent(this, AdminGererAbonnementActivity.class));
        } else if (id == R.id.nav_gerer_cours) {
            startActivity(new Intent(this, GererCoursActivity.class));
        } else if (id == R.id.nav_gerer_presence) {
            startActivity(new Intent(this, GererPresenceCoachActivity.class));
        } else if (id == R.id.nav_ajouter_cours) {
            startActivity(new Intent(this, AjouterCoursActivity.class));
        } else if (id == R.id.nav_ajouter_heure) {
            startActivity(new Intent(this, AjouterHeureTravailActivity.class));
        }

        // --- SECTION COACH ---
        else if (id == R.id.nav_coach_home) {
            startActivity(new Intent(this, CoachHomeActivity.class));
        }

        // --- SECTION CLIENT ---
        else if (id == R.id.nav_client_profile) {
            // Si on n'est pas déjà sur le profil
            if (!(this instanceof ClientProfileActivity)) {
                startActivity(new Intent(this, ClientProfileActivity.class));
            }
        } else if (id == R.id.nav_reserver_cours) {
            startActivity(new Intent(this, ReserverCoursActivity.class));
        }

        // --- DECONNEXION ---
        else if (id == R.id.nav_logout) {
            // Effacer le token
            SharedPreferences preferences = getSharedPreferences("GymAppPrefs", MODE_PRIVATE);
            preferences.edit().clear().apply();

            // Retour au login
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        // Fermer le menu après le clic
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
}