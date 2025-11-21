package com.example.gymconnectapplication;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import android.widget.Toast;

public class AdminHomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home); // Ton layout XML avec Drawer

        drawerLayout = findViewById(R.id.drawerLayout); // Vérifie l'ID !
        navView = findViewById(R.id.navView);

        navView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            int id = item.getItemId();

            if (id == R.id.nav_admin_home) {
                Toast.makeText(this, "Accueil Admin", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_logout) {
                finish();
            }

            return true;
        });
    }

    // Cette méthode est appelée depuis android:onClick du bouton menu
    public void openDrawer(View view) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
