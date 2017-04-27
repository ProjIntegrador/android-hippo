package br.com.enforce.hippov1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Produto extends AppCompatActivity {

    Context context;

    private ListView lv_produto;
    private ArrayList<String> produto;
    private ArrayAdapter<String>  adapter_produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        inicializaVariavel();
        inicializaAcao();
    }

    private void inicializaVariavel() {

        context = getBaseContext();

        lv_produto = (ListView) findViewById(R.id.lv_produto);

        // estou criando metodo para substituir ws
        gerarProduto(100);

        adapter_produto = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_list_item_1,
                produto
        );

        lv_produto.setAdapter(adapter_produto);
    }

    private void gerarProduto(int qtd) {

        produto = new ArrayList<>();

        for(int i = 1; i <= qtd;i++){
            produto.add("Produto - "+ String.valueOf(i));
        }

    }


    private void inicializaAcao() {



    }

}
