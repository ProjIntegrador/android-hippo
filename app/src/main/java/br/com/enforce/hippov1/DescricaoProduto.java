package br.com.enforce.hippov1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;

import br.com.enforce.hippov1.entities.Item;
import br.com.enforce.hippov1.rest.HippoServices;
import br.com.enforce.hippov1.rest.ProdutoRest;
import br.com.enforce.hippov1.rest.RetrofitInitializer;
import br.com.enforce.hippov1.tempdata.SingletonHippo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DescricaoProduto extends AppCompatActivity {

    private TextView nomeproduto;
    private ImageView imagem;
    private TextView preco;
    private TextView promocao;
    private Spinner quantidade;
    private TextView descproduto;
    private int idProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_produto);

        Intent intent = getIntent();
        idProduto = Integer.parseInt(intent.getStringExtra("idProduto"));

        nomeproduto = (TextView) findViewById(R.id.nomeProduto);
        imagem = (ImageView) findViewById(R.id.imagem);
        preco = (TextView)findViewById(R.id.precProduto);
        promocao = (TextView) findViewById(R.id.descontoPromocao);
        quantidade = (Spinner) findViewById(R.id.spin_qtd);
        descproduto = (TextView) findViewById(R.id.descProduto);

        HippoServices service = new RetrofitInitializer().getHippoServices();
        Call<ProdutoRest> retorno = service.obtemDetalheProduto(idProduto);
        retorno.enqueue(new Callback<ProdutoRest>() {
            @Override
            public void onResponse(Call<ProdutoRest> call, Response<ProdutoRest> response) {
                Log.i("retorno", response.body().toString());
                ProdutoRest prod = response.body();
                nomeproduto.setText(prod.getNomeProduto());
                preco.setText(prod.getPrecProduto().toString());
                descproduto.setText(prod.getDescProduto());

                //  Solucao para o SPINNER obtendo o limite de itens do estoque
                Integer[] intArray = new Integer[prod.getQtdMinEstoque()];
                for(int i = 0; i < prod.getQtdMinEstoque(); i++) {
                    intArray[i] = i;
                }
                ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(DescricaoProduto.this,android.R.layout.simple_spinner_dropdown_item, intArray);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                quantidade.setAdapter(adapter);




                /*  IMAGEM RENDER (tentativa)

                //  Obtem Response da imagem do banco que está inicialmente em binário
                String binaryStringResponse = response.body().getImagem();

                //  Convertendo String para Binario
                byte[] bynarybase = binaryStringResponse.getBytes();

                //  Pega Base Binária e converte para a Base64
                // byte[] encodedImage64 = Base64.encode(bynarybase, Base64.DEFAULT);

                //  Decodifica a base64 para o elemento R.id.imagem
                imagem.setImageBitmap(BitmapFactory.decodeByteArray(encodedImage64, 0, encodedImage64.length));

                //  FIM Bloco IMAGEM
                */

            }
            @Override
            public void onFailure(Call<ProdutoRest> call, Throwable t) {
                Log.e("falha", t.getMessage());
                finish();
            }

        });

    }

    public void add_cart(View v) {
        Log.i("Detalhe P", "idProduto "+idProduto);
        Log.i("Detalhe P", "quantidade "+quantidade.getSelectedItem().toString());
        Log.i("Detalhe P", "preco "+preco.getText());

        Item item = new Item();
        item.setIdProduto(new Long(idProduto));
        item.setNomeProduto(nomeproduto.getText().toString());
        item.setQtdProduto((Integer) quantidade.getSelectedItem());
        item.setPrecoVendaItem(new BigDecimal(preco.getText().toString()));

        SingletonHippo.Instance().addItem(item);

        Intent intent = new Intent(DescricaoProduto.this, Carrinho.class);
        startActivity(intent);
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