package br.com.enforce.hippov1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
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

public class DescricaoProduto extends AppCompatActivity {

    private TextView nomeproduto;
    private ImageView imagem;
    private TextView preco;
    private TextView promocao;
    private Spinner quantidade;
    private TextView descproduto;
    private int idProduto;
    private boolean flag;
    private BigDecimal promo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_produto);

        Intent intent = getIntent();
        idProduto = Integer.parseInt(intent.getStringExtra("idProduto"));

        nomeproduto = (TextView) findViewById(R.id.nomeProduto);
        imagem = (ImageView) findViewById(R.id.imagem);
        preco = (TextView) findViewById(R.id.precProduto);
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
                descproduto.setText(prod.getDescProduto());
                BigDecimal precoproduto = prod.getPrecProduto();
                BigDecimal desconto = BigDecimal.valueOf(prod.getDescontoPromocao());

                if (prod.getDescontoPromocao() == 0) {
                    preco.setText(prod.getPrecProduto().toString());
                    flag = false;
                } else {
                    promocao.setText(desconto.toString());
                    promocao.setVisibility(View.VISIBLE);
                    TextView labelPreco = (TextView) findViewById(R.id.lbl_precProduto);
                    labelPreco.setText("DE:                 R$ ");
                    TextView labelDescPromo = (TextView) findViewById(R.id.lbl_descontoPromocao);
                    labelDescPromo.setVisibility(View.VISIBLE);
                    labelDescPromo.setText("POR:       R$ ");
                    preco.setText(precoproduto.toString());
                    promo = desconto;
                    flag = true;
                }

                //  Solucao para o SPINNER obtendo o limite de itens do estoque
                Integer[] intArray = new Integer[prod.getQtdMinEstoque()];
                for (int i = 0; i < prod.getQtdMinEstoque(); i++) {
                    intArray[i] = i;
                }
                ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(DescricaoProduto.this, android.R.layout.simple_spinner_dropdown_item, intArray);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                adapter.notifyDataSetChanged();
                quantidade.setSelection(0, false);
                quantidade.setAdapter(adapter);

                //  IMAGEM
                String imgStringBase64 = prod.getImagem();
                //  CONDIÇÃO para impedir erro de NULL POINTER EXCEPTION quando vier uma Imagem NULL do BANCO / WS.
                if (imgStringBase64 == null || imgStringBase64 == "") {
                    Drawable myDrawable = getResources().getDrawable(R.drawable.no_image);
                    Bitmap noImg = ((BitmapDrawable) myDrawable).getBitmap();
                    imagem.setImageBitmap(noImg);
                } else {
                    byte[] decodedString = Base64.decode(imgStringBase64, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    imagem.setImageBitmap(decodedByte);
                }
                //  FIM IMAGEM
            }

            @Override
            public void onFailure(Call<ProdutoRest> call, Throwable t) {
                Log.e("falha", t.getMessage());
                finish();
            }

        });

    }

    public void add_cart(View v) {
        Log.i("Detalhe P", "idProduto " + idProduto);
        Log.i("Detalhe P", "quantidade " + quantidade.getSelectedItem().toString());
        Log.i("Detalhe P", "preco " + preco.getText());


        Item item = new Item();
        item.setIdProduto(new Long(idProduto));
        item.setNomeProduto(nomeproduto.getText().toString());
        // A linha abaixo define que ao clicar e adicionar o produto no carrinho
        // caso ele nao alterou a quantidade de 0 add 1 na quantidade ao singleton
        int numSpinner = 0;
        numSpinner = Integer.parseInt(String.valueOf(quantidade.getSelectedItemPosition()));
        if ( numSpinner == 0) {
            item.setQtdProduto(1);
        } else {
            item.setQtdProduto((Integer) quantidade.getSelectedItem());
        }

        if (flag != false) {
            item.setPrecoVendaItem(new BigDecimal(String.valueOf(promo)));
        } else {
            item.setPrecoVendaItem(new BigDecimal(preco.getText().toString()));
        }

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