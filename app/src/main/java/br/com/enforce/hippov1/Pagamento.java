package br.com.enforce.hippov1;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.enforce.hippov1.fragmentos.Boleto_op;
import br.com.enforce.hippov1.fragmentos.Cartao_op;

public class Pagamento extends Activity {
private Spinner spinner;
    private List<String> opcoes = new ArrayList<>();
    private String opcao;
    private Button enviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        spinner = (Spinner)findViewById(R.id.spinner_pag);
        opcoes.add("Cartão de Credito");
        opcoes.add("Boleto");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, opcoes);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);

        enviar=(Button)findViewById(R.id.enviar_dados_pag);
        enviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(Pagamento.this, "Dados Enviados Com Sucesso ,Retornando a Compra " , Toast.LENGTH_LONG).show();
                Intent i = new Intent(Pagamento.this, MainActivity.class);
                startActivity(i);
            }
        });

        //Método do Spinner para capturar o item selecionado
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                //pega nome pela posição
                opcao = parent.getItemAtPosition(posicao).toString();
                if(opcao.equals("Cartão de Credito")){

                    Fragment fragment;
                    fragment = new Cartao_op();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.pagamento_pager, fragment).commit();


                }
                else{
                    Fragment fragment;
                    fragment = new Boleto_op();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.pagamento_pager, fragment).commit();
                }
                //imprime um Toast na tela com o nome que foi selecionado
                Toast.makeText(Pagamento.this, "Forma Selecionada: " + opcao, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

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

