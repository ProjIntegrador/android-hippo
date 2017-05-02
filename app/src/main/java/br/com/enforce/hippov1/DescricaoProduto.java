package br.com.enforce.hippov1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import br.com.enforce.hippov1.rest.HippoServices;
import br.com.enforce.hippov1.rest.ProdutoRest;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_produto);

        nomeproduto = (TextView) findViewById(R.id.nomeProduto);
        imagem = (ImageView) findViewById(R.id.imagem);
        preco = (TextView)findViewById(R.id.precProduto);
        promocao = (TextView) findViewById(R.id.descontoPromocao);
        quantidade = (Spinner) findViewById(R.id.spin_qtd);
        descproduto = (TextView) findViewById(R.id.descProduto);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://191.252.61.93:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HippoServices service = retrofit.create(HippoServices.class);
        Call<ProdutoRest> retorno = service.obtemDetalheProduto(2);

        retorno.enqueue(new Callback<ProdutoRest>() {
            @Override
            public void onResponse(Call<ProdutoRest> call, Response<ProdutoRest> response) {
                Log.i("retorno", response.body().toString());
                nomeproduto.setText(response.body().getNome());
                preco.setText(response.body().getPreco().toString());

                /* String base = response.body().getImagem();
                byte[] imageBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
                Bitmap imagemDecodificada = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                imagem.setImageBitmap(imagemDecodificada); */

                descproduto.setText(response.body().getDescproduto());
            }

            @Override
            public void onFailure(Call<ProdutoRest> call, Throwable t) {
                Log.e("falha", t.getMessage());
                finish();
            }

        });




    }
}