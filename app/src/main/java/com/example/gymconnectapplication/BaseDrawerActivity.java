package com.example.gymconnectapplication;

import android.content.Intent;
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
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    // Comportement par défaut
                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        });
    }

    // Appelez cette méthode après setContentView dans chaque Activity
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

    // Méthode pour ouvrir le menu (appelée depuis le XML avec android:onClick)
    public void openDrawer(View view) {
        if (drawerLayout != null) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    // Gestion de la navigation du menu
    protected void handleNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.nav_admin_home) {
            startActivity(new Intent(this, AdminHomeActivity.class));
        } else if (id == R.id.nav_gerer_abonnements) {
            startActivity(new Intent(this, AdminGererAbonnementActivity.class));
        }

        else if (id == R.id.nav_gerer_cours) {
            startActivity(new Intent(this, GererCoursActivity.class));
        } else if (id == R.id.nav_gerer_presence) {
            startActivity(new Intent(this, GererPresenceCoachActivity.class));
        } else if (id == R.id.nav_ajouter_cours) {
            startActivity(new Intent(this, AjouterCoursActivity.class));
        } else if (id == R.id.nav_ajouter_heure) {
            startActivity(new Intent(this, AjouterHeureTravailActivity.class));
        }

        else if (id == R.id.nav_logout) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        //  Fermer le drawer APRÈS la navigation
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
}