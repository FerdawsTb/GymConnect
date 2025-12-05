package com.example.gymconnectapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

// IMPORTS RETROFIT
import com.example.gymconnectapplication.model.ClientProfileResponse;
import com.example.gymconnectapplication.network.RetrofitClient;
import com.example.gymconnectapplication.network.GymApiService;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientProfileActivity extends BaseDrawerActivity {

    private TextView clientName, clientEmail, memberSince;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile);

        // 1. Initialiser le Menu (Méthode du parent)
        setupDrawer();

        // 2. liaison entre variable clientName et le textlabel en xml
        clientName = findViewById(R.id.clientName);
        clientEmail = findViewById(R.id.clientEmail);
        memberSince = findViewById(R.id.memberSince);

        // 3. Charger les données
        fetchUserProfile();
    }

    private void fetchUserProfile() {
        SharedPreferences preferences = getSharedPreferences("GymAppPrefs", MODE_PRIVATE);
        String token = preferences.getString("auth_token", null);

        if (token == null) {
            // Token manquant -> Login
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        String authHeader = "Bearer " + token;
        GymApiService apiService = RetrofitClient.getApiService();
        // appelle du api
        Call<ClientProfileResponse> call = apiService.getProfile(authHeader);

        call.enqueue(new Callback<ClientProfileResponse>() {
            @Override
            public void onResponse(Call<ClientProfileResponse> call, Response<ClientProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    //enregistrement la reponse du url
                    ClientProfileResponse profile = response.body();
                    //on le affiche ici avec le variable que on le lier avec le textview en xml
                    clientName.setText(profile.getPrenom() + " " + profile.getNom());
                    clientEmail.setText(profile.getEmail());
                    memberSince.setText("Age: " + profile.getAge() + " ans | Tél: " + profile.getTelephone());
                } else {
                    if (response.code() == 403 || response.code() == 401) {
                        Toast.makeText(ClientProfileActivity.this, "Session expirée", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ClientProfileActivity.this, LoginActivity.class));
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<ClientProfileResponse> call, Throwable t) {
                Toast.makeText(ClientProfileActivity.this, "Erreur de connexion", Toast.LENGTH_SHORT).show();
            }
        });
    }
}