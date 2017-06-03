package br.com.enforce.hippov1.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

@JsonIgnoreProperties (ignoreUnknown = true)
public interface HippoServices {

    //      REQUISIÇAO DAS CATEGORIAS
    @GET("/webservices/categoria")
    Call<List<CategoriaRest>> responseString();

    //      REQUISIÇAO OBTENDO PRODUTOS DE UMA CATEGORIA
//    @JsonProperty("categoria")
    @GET("/webservices/produto")
    Call<List<ProdutoRest>> obtemProdutosPorCategoria(@Query("idCategoria") int idCategoria);

    //      PEGA INFORMAÇOES PARA TELA DE PRODUTO
    @GET("/webservices/produto/detalhe")
    Call<ProdutoRest> obtemDetalheProduto(@Query("idProduto") int idProduto);

    //      POST DE AUTENTICAÇAO do LOGIN
    @FormUrlEncoded
    @POST("/webservices/login")
    Call<Void> autenticaCliente(
            @Field("emailCliente") String emailCliente,
            @Field("senhaCliente") String senhaCliente
    );

    @GET("/webservices/cliente/")
    Call<ClienteLoginRest> clienteAutenticado();

    @POST("/webservices/cliente")
    Call<ClienteLoginRest> criarUsuario(@Body ClienteLoginRest cliente);

    @POST("/webservices/pedido")
    Call<Pedido> submetePedido(@Body Pedido pedido);

}