package br.com.enforce.hippov1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.enforce.hippov1.rest.ProdutoRest;
import br.com.enforce.hippov1.rest.RetrofitInitializer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProdutoCategoria extends AppCompatActivity {

    private LinearLayout ll_produtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_categoria);
        ll_produtos = (LinearLayout) findViewById(R.id.ll_produtos);

        Intent intent = getIntent();
        int idCategoria = Integer.parseInt(intent.getStringExtra("idCategoria"));

        if(idCategoria > 0 ){

            Call call = (Call) new RetrofitInitializer().getHippoServices().obtemProdutosPorCategoria(idCategoria);
            call.enqueue(new Callback<List<ProdutoRest>>() {
                @Override
                public void onResponse(final Call<List<ProdutoRest>> call, Response<List<ProdutoRest>> response) {

                    if (response.isSuccessful()) {
                        List<ProdutoRest> productCategoria = response.body();

                        if (productCategoria.size() > 0){
                            Log.e("Nome produto: ", "produtos : " + productCategoria.size());

                            for (ProdutoRest produto : productCategoria){
                                Log.e("produtos: ", produto.getIdProduto()+" : " + produto.getNomeProduto());
                                addProd1(produto);
                            }

                            Log.e("produtos: ", "produtos carregados com sucesso");
                        } else {
                            TextView infoNoProducts = (TextView) findViewById(R.id.tv_noproducts);
                            Button btnVoltaCategoria = (Button) findViewById(R.id.btn_returncategorias);
                            infoNoProducts.setVisibility(View.VISIBLE);
                            btnVoltaCategoria.setVisibility(View.VISIBLE);

                            btnVoltaCategoria.setOnClickListener(new Button.OnClickListener() {
                                public void onClick(View v) {

                                    Intent intent = new Intent(ProdutoCategoria.this, Categorias.class);
                                    startActivity(intent);

                                }
                            });
                        }

                    }

                }

                @Override
                public void onFailure(Call<List<ProdutoRest>> call, Throwable t) {
                    Log.e( "falha: " , t.getMessage() );
                }

            });

        } else {
            Log.e( "falha: " , "Nenhuma categoria retornada" ); //TODO tratar
        }

        /*  EM CASO DE FALHA NA CHAMADA */
        Log.e("produtos: ", "produtos deveriam ter sido carregados");

    }

    private void addProd1(ProdutoRest produto) {
        CardView cardView = (CardView) LayoutInflater.from(this).inflate(R.layout.cardview_produtos, ll_produtos, false);

        TextView idcategoria = (TextView) cardView.findViewById(R.id.id_produto);
        TextView tituloCategoria = (TextView) cardView.findViewById(R.id.nome_produto);

        tituloCategoria.setText(produto.getNomeProduto());
        idcategoria.setText(produto.getIdProduto().toString());

        //  Na criação do CARDVIEW, definir o Listener do ONCLICK
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = ((TextView)v.findViewById(R.id.id_produto)).getText().toString();
                Intent intent = new Intent(ProdutoCategoria.this, DescricaoProduto.class);
                intent.putExtra( "idProduto", id);
                startActivity(intent);
            }
        });

        ll_produtos.addView(cardView);
    }

    //  MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);

        //  FAB  -   Floating Action Button
        FloatingActionButton fabqr = (FloatingActionButton) findViewById(R.id.fab_qrcode);
        fabqr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProdutoCategoria.this, ReadQr.class);
                startActivity(intent);

            }
        });
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