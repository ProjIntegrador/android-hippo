package br.com.enforce.hippov1;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.com.enforce.hippov1.entities.Item;
import br.com.enforce.hippov1.entities.TipoPagto;
import br.com.enforce.hippov1.fragmentos.Boleto_op;
import br.com.enforce.hippov1.fragmentos.Cartao_op;
import br.com.enforce.hippov1.rest.ClienteLoginRest;
import br.com.enforce.hippov1.rest.HippoServices;
import br.com.enforce.hippov1.rest.Pedido;
import br.com.enforce.hippov1.rest.RetrofitInitializer;
import br.com.enforce.hippov1.tempdata.SingletonHippo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pagamento extends AppCompatActivity {

    private Spinner spinner;
    private Button enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HippoServices service = new RetrofitInitializer(getApplicationContext()).getHippoServices();
        Call<ClienteLoginRest> retorno = service.clienteAutenticado();

        retorno.enqueue(new Callback<ClienteLoginRest>() {
            @Override
            public void onResponse(Call<ClienteLoginRest> call, Response<ClienteLoginRest> response) {

                if (response.code() == 401) {
                    Intent intent = new Intent(Pagamento.this, Login.class);
                    startActivity(intent);
                    return;
                }

                if (response.isSuccessful()) {
                    Log.i("retorno", "raw : "+response.raw().body());

                    ClienteLoginRest cliente = response.body();
                    SingletonHippo.Instance().setCliente(cliente);

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

        setContentView(R.layout.activity_pagamento);
        spinner = (Spinner)findViewById(R.id.spinner_pag);

        spinner.setAdapter(new ArrayAdapter<TipoPagto>(this, android.R.layout.simple_list_item_1, TipoPagto.values()));

        enviar=(Button)findViewById(R.id.enviar_dados_pag);
        enviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                TipoPagto tipoPagto = (TipoPagto) spinner.getSelectedItem();
                ClienteLoginRest cliente = SingletonHippo.Instance().getCliente();
                List<Item> itens = SingletonHippo.Instance().getItens();

                Pedido pedido = new Pedido();
                pedido.setIdCliente(cliente.getIdCliente());
                pedido.setIdTipoPagto(tipoPagto.getIdTipoPagto());
                pedido.setIdAplicacao(2);
                pedido.setIdStatus(1);
                pedido.setItens(itens);

                HippoServices service = new RetrofitInitializer(getApplicationContext()).getHippoServices();
                Call<Pedido> retorno = service.submetePedido(pedido);

                retorno.enqueue(new Callback<Pedido>() {
                    @Override
                    public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                        if (response.isSuccessful()) {
                            Log.i("retorno", "raw : "+response.raw().body());

                        } else {
                            Log.i("return_error", response.body().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<Pedido> call, Throwable t) {
                        Log.e("falha", t.getMessage());
                        finish();
                    }

                });

//                Toast.makeText(Pagamento.this, "Dados Enviados Com Sucesso ,Retornando a Compra " , Toast.LENGTH_LONG).show();
//                Intent i = new Intent(Pagamento.this, MainActivity.class);
//                startActivity(i);
            }
        });

        //Método do Spinner para capturar o item selecionado
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                //pega nome pela posição
                TipoPagto tipoPagto = (TipoPagto) parent.getItemAtPosition(posicao);
                if(tipoPagto == TipoPagto.CartaoDeCredito){

                    Fragment fragment;
                    fragment = new Cartao_op();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.pagamento_pager, fragment).commit();


                }else{
                    Fragment fragment;
                    fragment = new Boleto_op();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.pagamento_pager, fragment).commit();
                }
                //imprime um Toast na tela com o nome que foi selecionado
                Toast.makeText(Pagamento.this, "Forma Selecionada: " + tipoPagto.getTipoPagto(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

