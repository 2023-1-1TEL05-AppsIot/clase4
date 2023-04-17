package com.example.clase4.models;


import retrofit2.Call;
import retrofit2.http.GET;

public interface TypicodeRepository {

    @GET("/typicode/demo/profile")
    Call<Profile> getProfile();
}
