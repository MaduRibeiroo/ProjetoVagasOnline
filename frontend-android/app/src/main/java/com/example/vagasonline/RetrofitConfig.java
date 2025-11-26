package com.example.vagasonline;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public VagaService getVagaService() {
        return this.retrofit.create(VagaService.class);
    }

    public InteresseService getInteresseService() {
        return this.retrofit.create(InteresseService.class);
    }
}
