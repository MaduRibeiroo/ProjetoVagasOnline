package com.example.vagasonline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {

    Interesse interesse;
    private Candidato candidato;
    private Vagas vaga;
   private EditText etNome,etCpf,etEmail,etTelefone,etFormacao;
    private Button btnVoltar,btnRegistrarInteresse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2); // seu layout

        // Ligando os componentes
        etNome = findViewById(R.id.etNome);
        etCpf = findViewById(R.id.etCpf);
        etEmail = findViewById(R.id.etEmail);
        etTelefone = findViewById(R.id.etTelefone);
        etFormacao = findViewById(R.id.etFormacao);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnRegistrarInteresse=findViewById(R.id.btnRegistrarInteresse);
        //pegando objeto serializado
        vaga = (Vagas) getIntent().getSerializableExtra("vaga");
        candidato = new Candidato();
        interesse= new Interesse();


        // Botão de voltar
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // apenas fecha e retorna para a Activity anterior
            }
        });


        //butão de registrar interesse
        btnRegistrarInteresse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                candidato.setNome(etNome.getText().toString());
                candidato.setCpf(etCpf.getText().toString());
                candidato.setEmail(etEmail.getText().toString());
                candidato.setTelefone(etTelefone.getText().toString());
                candidato.setFormacao(etFormacao.getText().toString());
                registrarInteresse();
                Log.d("INTERESSE_DEBUG", new Gson().toJson(interesse));

            }
        });

    }


    private void registrarInteresse() {

        interesse.setVaga(vaga);
        interesse.setCandidato(candidato);

        InteresseService service = new RetrofitConfig().getInteresseService();

        Call<String> call = service.enviarInteresse(interesse);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity2.this, response.body(), Toast.LENGTH_LONG).show();
                } else {
                    try {
                        String erroBruto = response.errorBody().string();
                        Log.e("API_ERROR_BODY", erroBruto);

                        String msgFinal = tratarMensagemErro(erroBruto);

                        Toast.makeText(MainActivity2.this, msgFinal, Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        Toast.makeText(MainActivity2.this,
                                "Erro inesperado: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("API_FAIL", "Falha: " + t.getMessage());
                t.printStackTrace();

                Toast.makeText(MainActivity2.this,
                        "Falha na conexão: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private String tratarMensagemErro(String erro) {

        // 1) Tentar ler JSON {"message": "..."}
        try {
            JSONObject obj = new JSONObject(erro);
            if (obj.has("message")) return obj.getString("message");
        } catch (Exception ignored) {}

        // 2) Backend pode ter retornado HTML — extrair só o texto
        if (erro.contains("<") && erro.contains(">")) {
            return erro.replaceAll("<[^>]*>", "").trim();
        }

        // 3) Caso seja texto puro
        if (!erro.isEmpty()) return erro;

        return "Ocorreu um erro desconhecido.";
    }


}
