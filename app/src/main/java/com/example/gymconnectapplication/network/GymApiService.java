package com.example.gymconnectapplication.network;

import com.example.gymconnectapplication.model.ClientProfileResponse;
import com.example.gymconnectapplication.model.LoginRequest;
import com.example.gymconnectapplication.model.LoginResponse;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface GymApiService {
    // L'URL complète sera : http://10.0.2.2:7070/api/auth/login
    @POST("api/auth/login")
    Call<LoginResponse> loginClient(@Body LoginRequest request);

    @POST("/api/auth/register")
    Call<RegisterResponse> registerClient(@Body RegisterRequest request);


    // L'URL complète sera : http://10.0.2.2:7070/api/auth/profile
    @GET("api/auth/profile")
    Call<ClientProfileResponse> getProfile(@Header("Authorization") String token);
}