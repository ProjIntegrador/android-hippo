package br.com.enforce.hippov1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import br.com.enforce.hippov1.rest.HippoServices;
import br.com.enforce.hippov1.rest.ClienteLoginRest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    private EditText emailCliente;
    private EditText senhaCliente;
    private Button buttonEnviar;
    private Button buttonNvCadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailCliente = (EditText) findViewById(R.id.emailCliente);
        senhaCliente = (EditText) findViewById(R.id.senhaCliente);
        buttonEnviar = (Button) findViewById(R.id.buttonEnviar);
        buttonNvCadastro = (Button) findViewById(R.id.buttonNvCadastro);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://191.252.61.93:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HippoServices service = retrofit.create(HippoServices.class);
        Call<ClienteLoginRest> retorno = service.autenticaCliente( emailCliente , senhaCliente);

        retorno.enqueue(new Callback<ClienteLoginRest>() {
            @Override
            public void onResponse(Call<ClienteLoginRest> call, Response<ClienteLoginRest> response) {
                if (response.isSuccessful()) {
                    Log.i("retorno", response.body().toString());
                }else {
                    Log.i("return_error", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<ClienteLoginRest> call, Throwable t) {
                Log.e("falha", t.getMessage());
                finish();
            }

        });



    }
}
