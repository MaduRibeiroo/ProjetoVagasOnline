package com.example.vagasonline;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InteresseService {

    @POST("apis/interesse")
    Call<String> enviarInteresse(@Body Interesse interesse);
}