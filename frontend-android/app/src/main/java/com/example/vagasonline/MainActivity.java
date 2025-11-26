package com.example.vagasonline;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView lvVagas;
    List<Vagas> vagasList=null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lvVagas = (ListView) findViewById(R.id.lvVagas);
        carregarVagas();
        lvVagas.setOnItemClickListener((parent, view, position, id) -> {
            Vagas vagaSelecionada = vagasList.get(position);

            Intent intent = new Intent(MainActivity.this, MainActivity2.class);

            intent.putExtra("vaga", vagaSelecionada);

            startActivity(intent);
        });

    }



    private void carregarVagas(){

        Call<List<Vagas>> call =  new RetrofitConfig().getVagaService().getTodasVagas();
        call.enqueue(new Callback<List<Vagas>>() {


            @Override
            public void onResponse(Call<List<Vagas>> call, Response<List<Vagas>> response) {
                Toast.makeText(MainActivity.this, "Carregou  vagas", Toast.LENGTH_SHORT).show();
                if(response.isSuccessful()){

                    vagasList = response.body();
                    VagasAdapter adapter = new
                            VagasAdapter(MainActivity.this,R.layout.list_view_layout,vagasList);
                    lvVagas.setAdapter(adapter);
                    Toast.makeText(MainActivity.this, "Carregou " + vagasList.size() + " vagas", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call<List<Vagas>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "DEU RUIM  vagas", Toast.LENGTH_SHORT).show();
                vagasList=null;
                Log.e("API", "Erro de conexão: " + t.getMessage());
                t.printStackTrace();
            }


        });
    }

}