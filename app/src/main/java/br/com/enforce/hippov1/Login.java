package br.com.enforce.hippov1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.enforce.hippov1.rest.HippoServices;
import br.com.enforce.hippov1.rest.RetrofitInitializer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        buttonNvCadastro.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Cadastro.class);
                startActivity(intent);
            }
        });



        buttonEnviar = (Button) findViewById(R.id.buttonEnviar);
        buttonEnviar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                //  INICIA COMUNICAÇÃO COM O WS de Login
                HippoServices service = new RetrofitInitializer(getApplicationContext()).getHippoServices();
                Call<Void> retorno = service.autenticaCliente(emailCliente.getText().toString(), senhaCliente.getText().toString());

                retorno.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Log.i("retorno", "raw : " + response.raw().body());
//                            ClienteLoginRest cliente = response.body();
//                            Log.i("retorno", "id : "+cliente.getIdCliente());
                            Intent intent = new Intent(Login.this, Pagamento.class);
                            startActivity(intent);
                            return;
                        } else {
                            Log.i("return_error", "HTTP respondeu: " + String.valueOf(response.raw().code()));
                            Toast.makeText(Login.this, "Email ou Senha está incorreto. " +
                                    "Reveja os dados inseridos e Tente novamente.", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(Login.this, Login.class);
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e("falha", t.getMessage());
                        finish();
                    }

                });

            }
        });

    }

    //  MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.ophome) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.opcar) {
            Intent intent = new Intent(this, Carrinho.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.opabout) {
            Intent intent = new Intent(this, Sobre.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
