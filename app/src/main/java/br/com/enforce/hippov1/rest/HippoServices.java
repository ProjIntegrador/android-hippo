package br.com.enforce.hippov1.rest;

import android.widget.EditText;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HippoServices {

    //      REQUISIÇAO DAS CATEGORIAS
    @GET("/webservices/categoria")
    public void responseString(Callback<CategoriaRest> response);
    //Call<CategoriaRest> obterCategorias(@Query("idCategoria") int idCategoria);

        //      PEGA INFORMAÇOES PARA TELA DE PRODUTO
        @GET("/webservices/produto/detalhe")
        Call<ProdutoRest> obtemDetalheProduto(@Query("idProduto") int idProduto);

        //      POST DE AUTENTICAÇAO do LOGIN
        @FormUrlEncoded
        @POST("/webservices/login")
        Call<ClienteLoginRest> autenticaCliente(
                @Field("emailCliente") EditText emailCliente,
                @Field("senhaCliente") EditText senhaCliente
        );

    Call<CategoriaRest> responseString();

}