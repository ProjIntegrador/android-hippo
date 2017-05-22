package br.com.enforce.hippov1.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HippoServices {

    //      REQUISIÇAO DAS CATEGORIAS
    @GET("/webservices/categoria")
    Call<List<CategoriaRest>> responseString();

    //      PEGA INFORMAÇOES PARA TELA DE PRODUTO
    @GET("/webservices/produto/detalhe")
    Call<ProdutoRest> obtemDetalheProduto(@Query("idProduto") int idProduto);

    //      POST DE AUTENTICAÇAO do LOGIN
    @FormUrlEncoded
    @POST("/webservices/login")
    Call<ClienteLoginRest> autenticaCliente(
            @Field("emailCliente") String emailCliente,
            @Field("senhaCliente") String senhaCliente
    );

}