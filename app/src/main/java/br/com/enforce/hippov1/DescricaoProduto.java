package br.com.enforce.hippov1;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class DescricaoProduto extends Activity {


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
        Call<ProdutoRest> retorno = service.obtemDetalheProduto(5);

        retorno.enqueue(new Callback<ProdutoRest>() {
            @Override
            public void onResponse(Call<ProdutoRest> call, Response<ProdutoRest> response) {
                Log.i("retorno", response.body().toString());
                nomeproduto.setText(response.body().getNome());
                preco.setText(response.body().getPreco().toString());

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