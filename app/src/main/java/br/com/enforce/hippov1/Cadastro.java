package br.com.enforce.hippov1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.com.enforce.hippov1.rest.ClienteLoginRest;
import br.com.enforce.hippov1.rest.HippoServices;
import br.com.enforce.hippov1.rest.RetrofitInitializer;
import br.com.enforce.hippov1.tempdata.SingletonHippo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cadastro extends AppCompatActivity {

    private EditText nomeCompletoCliente;
    private EditText emailCliente;
    private EditText senhaCliente;
    private EditText celularCliente;
    private EditText cpfCliente;
    private CheckBox cbx_promosao_por_email;
    private Button btn_cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nomeCompletoCliente = (EditText) findViewById(R.id.nomeCompletoCliente);
        emailCliente = (EditText) findViewById(R.id.emailCliente);
        senhaCliente = (EditText) findViewById(R.id.senhaCliente);
        celularCliente = (EditText) findViewById(R.id.celularCliente);
        cpfCliente = (EditText) findViewById(R.id.cpfCliente);
        cbx_promosao_por_email = (CheckBox) findViewById(R.id.cbx_promosao_por_email);
        btn_cadastrar = (Button) findViewById(R.id.btn_cadastrar);


        btn_cadastrar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                ClienteLoginRest clienteLoginRest = new ClienteLoginRest();
                clienteLoginRest.setNomeCompletoCliente(nomeCompletoCliente.getText().toString());
                clienteLoginRest.setCpfcliente(cpfCliente.getText().toString());
                clienteLoginRest.setEmailCliente(emailCliente.getText().toString());
                clienteLoginRest.setSenhaCliente(senhaCliente.getText().toString());
                clienteLoginRest.setCelularCliente(celularCliente.getText().toString());

                if ( nomeCompletoCliente == null || cpfCliente == null ||
                        emailCliente == null || senhaCliente == null
                        || celularCliente == null) {
                    Toast.makeText(Cadastro.this, "Existem Campos em Branco, preencha todos os campos.", Toast.LENGTH_LONG).show();
                } else {
                    HippoServices service = new RetrofitInitializer(getApplicationContext()).getHippoServices();
                    Call<ClienteLoginRest> retorno = service.criarUsuario(clienteLoginRest);
                    retorno.enqueue(new Callback<ClienteLoginRest>() {
                        @Override
                        public void onResponse(Call<ClienteLoginRest> call, Response<ClienteLoginRest> response) {
                            if (response.isSuccessful()) {
                                Log.i("retorno", "raw : " + response.raw().body());

                                ClienteLoginRest cliente = response.body();
                                SingletonHippo.Instance().setCliente(cliente);

                                if (SingletonHippo.Instance().getCliente() != null) {
                                    Intent intent = new Intent(Cadastro.this, Pagamento.class);
                                    startActivity(intent);
                                }
                                Log.i("retorno", "id : " + cliente.getIdCliente());
                            } else {
                                Log.i("return_error", "HTTP respondeu: " + String.valueOf(response.raw().code()));
                                Toast.makeText(Cadastro.this, "Não foi possível criar seu usuário. " +
                                        "Por gentileza, repita o processo novamente.", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Cadastro.this, Cadastro.class);
                                startActivity(i);
                            }

                        }

                        @Override
                        public void onFailure(Call<ClienteLoginRest> call, Throwable t) {
                            Log.e("falha", t.getMessage());
                        }

                    });
                }

            }

        });

    }
}