package br.com.enforce.hippov1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import br.com.enforce.hippov1.rest.ProdutoRest;
import br.com.enforce.hippov1.rest.RetrofitInitializer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProdutoCategoria extends AppCompatActivity {

    private LinearLayout ll_produtos;
    private boolean flagResult = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_categoria);
        ll_produtos = (LinearLayout) findViewById(R.id.ll_produtos);

        Intent intent = getIntent();
        final int idCategoria = Integer.parseInt(intent.getStringExtra("idCategoria"));

        if (idCategoria > 0) {

            Call call = (Call) new RetrofitInitializer().getHippoServices().obtemProdutosPorCategoria(idCategoria);
            call.enqueue(new Callback<List<ProdutoRest>>() {
                @Override
                public void onResponse(final Call<List<ProdutoRest>> call, Response<List<ProdutoRest>> response) {

                    if (response.isSuccessful()) {
                        List<ProdutoRest> productCategoria = response.body();

                        if (productCategoria.size() > 0) {
                            Log.e("Nome produto: ", "produtos : " + productCategoria.size());
                            flagResult = true;
                            for (ProdutoRest produto : productCategoria) {
                                Log.e("produtos: ", produto.getIdProduto() + " : " + produto.getNomeProduto());
                                addProd1(produto);
                            }

                            Log.e("produtos: ", "produtos carregados com sucesso da Categoria com id = " + idCategoria);
                        } else {
                            TextView infoNoProducts = (TextView) findViewById(R.id.tv_noproducts);
                            Button btnVoltaCategoria = (Button) findViewById(R.id.btn_returncategorias);
                            infoNoProducts.setVisibility(View.VISIBLE);
                            btnVoltaCategoria.setVisibility(View.VISIBLE);
                            flagResult = false;
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
                    Log.e("falha: ", t.getMessage());
                }

            });

        } else {
            Log.e("falha: ", "Nenhuma categoria retornada"); //TODO tratar
        }

        /*  EM CASO DE FALHA NA CHAMADA */
        Log.e("produtos: ", "produtos deveriam ter sido carregados");
        if (flagResult == false){
            TextView infoNoProducts = (TextView) findViewById(R.id.tv_noproducts);
            Button btnVoltaCategoria = (Button) findViewById(R.id.btn_returncategorias);
            infoNoProducts.setVisibility(View.VISIBLE);
            btnVoltaCategoria.setVisibility(View.VISIBLE);
            flagResult = false;
            btnVoltaCategoria.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {

                    Intent intent = new Intent(ProdutoCategoria.this, Categorias.class);
                    startActivity(intent);

                }
            });
        }

    }

    private void addProd1(ProdutoRest produto) {
        CardView cardView = (CardView) LayoutInflater.from(this).inflate(R.layout.cardview_produtos, ll_produtos, false);

        TextView idcategoria = (TextView) cardView.findViewById(R.id.id_produto);
        TextView tituloCategoria = (TextView) cardView.findViewById(R.id.nome_produto);
        TextView precoProdutoCat = (TextView) cardView.findViewById(R.id.preco_produto_cat);
        ImageView imagProduto = (ImageView) cardView.findViewById(R.id.img_produto_cat);

        idcategoria.setText(produto.getIdProduto().toString());
        tituloCategoria.setText(produto.getNomeProduto());
        String valorProduto = String.format(Locale.getDefault(), "%.2f", produto.getPrecProduto());
        precoProdutoCat.setText("R$   " + valorProduto);

        //  IMAGEM
        String imgStringBase64 = produto.getImagem();
        //  CONDIÇÃO para impedir erro de NULL POINTER EXCEPTION quando vier uma Imagem NULL do BANCO / WS.
        if (imgStringBase64 == null || imgStringBase64 == "") {
            Drawable myDrawable = getResources().getDrawable(R.drawable.no_image);
            Bitmap noImg = ((BitmapDrawable) myDrawable).getBitmap();
            imagProduto.setImageBitmap(noImg);
        } else {
            byte[] decodedString = Base64.decode(imgStringBase64, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imagProduto.setImageBitmap(decodedByte);
        }
        //  FIM IMAGEM

        //  Na criação do CARDVIEW, definir o Listener do ONCLICK
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = ((TextView) v.findViewById(R.id.id_produto)).getText().toString();
                Intent intent = new Intent(ProdutoCategoria.this, DescricaoProduto.class);
                intent.putExtra("idProduto", id);
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