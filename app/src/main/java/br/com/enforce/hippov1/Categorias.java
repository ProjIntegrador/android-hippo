package br.com.enforce.hippov1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import br.com.enforce.hippov1.rest.CategoriaRest;
import br.com.enforce.hippov1.rest.HippoServices;
import br.com.enforce.hippov1.rest.RetrofitInitializer;
import br.com.enforce.hippov1.tempdata.SingletonHippo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Categorias extends AppCompatActivity {

    private ViewGroup conteinerRes;
    private TextView lblEscolhaCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        conteinerRes = (ViewGroup) findViewById(R.id.container);

        Call call = (Call) new RetrofitInitializer().getHippoServices().responseString();
        call.enqueue(new Callback<List<CategoriaRest>>() {
            @Override
            public void onResponse(final Call<List<CategoriaRest>> call, Response<List<CategoriaRest>> response) {

                if (response.isSuccessful()) {
                    List<CategoriaRest> totalCat = response.body();
                    lblEscolhaCategoria = (TextView) findViewById(R.id.tv_escolha_cat);

                    if (totalCat.size() > 0) {
                        Log.e("Nome Categoria: ", "categorias : " + totalCat.size());

                        for (CategoriaRest categoria : totalCat) {
                            addItem(categoria.getNomeCategoria(), categoria.getIdCategoria());
                        }
                    } else {
                        Log.e("Sem categorias", "aaaaa");

                        final Snackbar snackbar = Snackbar
                                .make(findViewById(R.id.container), "Impossivel buscar as categorias no momento", Snackbar.LENGTH_INDEFINITE)
                                .setAction("Tentar Novamente", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                    }
                                });

                        View sbView = snackbar.getView();
                        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                        textView.setTextColor(Color.GREEN);
                        snackbar.show();
                    }

                } else {
                    Log.e("Nome Categoria: ", "aaaaa");
                }
            }

            @Override
            public void onFailure(Call<List<CategoriaRest>> call, Throwable t) {
                Log.e("falha", t.getMessage());

                final Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.container), "Falha ao Localizar as Categorias", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Tentar Novamente", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl("http://191.252.61.93:8080/")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                HippoServices service = new RetrofitInitializer().getHippoServices();
                                final Call<List<CategoriaRest>> retorno = service.responseString();

                                retorno.enqueue(new Callback<List<CategoriaRest>>() {
                                    @Override
                                    public void onResponse(Call<List<CategoriaRest>> call, Response<List<CategoriaRest>> response) {

                                        if (response.isSuccessful()) {
                                            List<CategoriaRest> totalCat = response.body();

                                            if (totalCat.size() > 0) {
                                                Log.e("Nome Categoria: ", "categorias : " + totalCat.size());

                                                for (CategoriaRest categoria : totalCat) {
                                                    addItem(categoria.getNomeCategoria(), categoria.getIdCategoria());
                                                }
                                            } else {
                                                Log.e("Sem categorias", "aaaaa");

                                                final Snackbar snackbar = Snackbar
                                                        .make(findViewById(R.id.container), "Impossivel buscar as categorias no momento", Snackbar.LENGTH_INDEFINITE)
                                                        .setAction("Tentar Novamente", new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                            }
                                                        });

                                                View sbView = snackbar.getView();
                                                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                                                textView.setTextColor(Color.GREEN);
                                                snackbar.show();
                                            }
                                        } else {
                                            Log.e("Nome Categoria: ", "aaaaa");
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<List<CategoriaRest>> call, Throwable t) {
                                        Log.e("falha", t.getMessage());

                                        final Snackbar snackbar = Snackbar
                                                .make(findViewById(R.id.container), "Falha ao Localizar as Categorias", Snackbar.LENGTH_INDEFINITE)
                                                .setAction("Tentar Novamente", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {


                                                    }
                                                });

                                        View sbView = snackbar.getView();
                                        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                                        textView.setTextColor(Color.GREEN);
                                        snackbar.show();
                                        // finish();
                                    }
                                });
                            }
                        });

                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.GREEN);
                snackbar.show();
                // finish();
            }
        });
    }

    private void addItem(String categoryTitle, final Long idcat) {

        CardView cardView = (CardView) LayoutInflater.from(this).inflate(R.layout.cardview_categoria, conteinerRes, false);

        TextView tituloCategoria = (TextView) cardView.findViewById(R.id.titulo);
        TextView idcategoria = (TextView) cardView.findViewById(R.id.idcat);

        tituloCategoria.setText(categoryTitle);
        idcategoria.setText(idcat.toString());

        //  Na criação do CARDVIEW, definir o Listener do ONCLICK
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String id = ((TextView)v.findViewById(R.id.idcat)).getText().toString();
            Intent intent = new Intent(Categorias.this, ProdutoCategoria.class);
            intent.putExtra( "idCategoria", id);
            startActivity(intent);
            }
        });

        conteinerRes.addView(cardView);
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
