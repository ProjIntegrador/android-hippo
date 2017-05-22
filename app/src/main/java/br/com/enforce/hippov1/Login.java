package br.com.enforce.hippov1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.enforce.hippov1.rest.AddCookiesInterceptor;
import br.com.enforce.hippov1.rest.HippoServices;
import br.com.enforce.hippov1.rest.ClienteLoginRest;
import br.com.enforce.hippov1.rest.ReceivedCookiesInterceptor;
import okhttp3.OkHttpClient;
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

        buttonNvCadastro = (Button) findViewById(R.id.buttonNvCadastro);


        buttonEnviar = (Button) findViewById(R.id.buttonEnviar);

        buttonEnviar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {


                OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
                okHttpClient.interceptors().add(new AddCookiesInterceptor(getApplicationContext()));
                okHttpClient.interceptors().add(new ReceivedCookiesInterceptor(getApplicationContext()));

                Log.i("senha", ""+senhaCliente.getText().toString());
                //  INICIA COMUNICAÇÃO COM O WS de Login
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://191.252.61.93:8080/")
                        .client(okHttpClient.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                HippoServices service = retrofit.create(HippoServices.class);
                Call<ClienteLoginRest> retorno = service.autenticaCliente(emailCliente.getText().toString(), senhaCliente.getText().toString());

                retorno.enqueue(new Callback<ClienteLoginRest>() {
                    @Override
                    public void onResponse(Call<ClienteLoginRest> call, Response<ClienteLoginRest> response) {
                        if (response.isSuccessful()) {
                            Log.i("retorno", "raw : "+response.raw().body());
                            ClienteLoginRest cliente = response.body();
                            Log.i("retorno", "id : "+cliente.getIdCliente());
                        } else {
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
        });


        /* @Override
        protected void onPostExecute(String emailCliente, String senhaCliente){




        }*/


    }
}
