package com.example.gymconnectapplication;

import android.os.Bundle;

public class AdminHomeActivity extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);


        setupDrawer();
    }
}