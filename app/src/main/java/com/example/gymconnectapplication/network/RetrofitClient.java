package com.example.gymconnectapplication.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    // 10.0.2.2 est l'adresse spéciale pour que l'émulateur accède à ton PC (localhost)
    private static final String BASE_URL = "http://10.0.2.2:7070/";
    private static Retrofit retrofit = null;

    public static GymApiService getApiService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(GymApiService.class);
    }
}