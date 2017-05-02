package br.com.enforce.hippov1.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HippoServices {


        @GET("/webservices/produto/detalhe")
        Call<ProdutoRest> obtemDetalheProduto(@Query("idProduto") int idProduto);

}
