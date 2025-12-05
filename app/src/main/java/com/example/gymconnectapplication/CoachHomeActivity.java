package com.example.gymconnectapplication;

import android.os.Bundle;

// Il suffit d'hériter de BaseDrawerActivity
public class CoachHomeActivity extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
=======
        // On charge le design spécifique du Coach
>>>>>>> 6f5ae1d ( liaison avec BD)
        setContentView(R.layout.activity_coach_home);

        // On active le menu latéral (méthode de BaseDrawerActivity)
        setupDrawer();
    }
}