package br.com.enforce.hippov1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.enforce.hippov1.rest.CategoriaRest;
import br.com.enforce.hippov1.rest.HippoServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Categorias extends AppCompatActivity {

    private ViewGroup conteinerRes;
    private String nomeCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        conteinerRes = (ViewGroup) findViewById(R.id.container);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://191.252.61.93:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HippoServices service = retrofit.create(HippoServices.class);
        final Call<List<CategoriaRest>> retorno = service.responseString();

        retorno.enqueue(new Callback<List<CategoriaRest>>() {
            @Override
            public void onResponse(Call<List<CategoriaRest>> call, Response<List<CategoriaRest>> response) {

                if (response.isSuccessful()) {
                    List<CategoriaRest> totalCat = response.body();

                    if (totalCat.size() > 0) {
                        Log.e("Nome Categoria: ", "categorias : " + totalCat.size());

                        for (CategoriaRest categoria : totalCat) {
                            addItem(categoria.getNome());
                        }
                    } else {
                        Log.e("Sem categoria", "aaaaa");
                    }

                } else {
                    Log.e("Nome Categoria: ", "aaaaa");
                }

            }

            @Override
            public void onFailure(Call<List<CategoriaRest>> call, Throwable t) {
                Log.e("falha", t.getMessage());
                finish();
            }

        });
    }

    private void addItem(String categoryTitle) {

        CardView cardView = (CardView) LayoutInflater.from(this).inflate(R.layout.element_card_layout, conteinerRes, false);

        TextView tituloCategoria = (TextView) cardView.findViewById(R.id.titulo);
        //TextView mensagem = (TextView) cardView.findViewById(R.id.mensagem);
        tituloCategoria.setText(categoryTitle);
        // mensagem.setText();

        //  Caso seja uma boa uma imagem, podemos incorporar incluindo o codigo abaixo e no for um Switch Case para setar uma img de cada Categoria
        /*ImageView RenderizarImageInId = (ImageView) cardView.findViewById(R.id.imagem);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.loading)
                .showImageForEmptyUri(R.drawable.no_image)
                .showImageOnFail(R.drawable.no_image)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        imageLoader.displayImage(urlArrposition, RenderizarImageInId, options);
        */

        conteinerRes.addView(cardView);
    }
}
