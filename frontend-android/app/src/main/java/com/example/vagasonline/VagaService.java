package com.example.vagasonline;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface VagaService {
    @GET("apis/vagas/get-all")
    Call<List<Vagas>> getTodasVagas();
}
