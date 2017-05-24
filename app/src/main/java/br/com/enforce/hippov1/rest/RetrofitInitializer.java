package br.com.enforce.hippov1.rest;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInitializer {

    private final Retrofit retrofit;

    public RetrofitInitializer() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://191.252.61.93:8080/webservices/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

//    @Override
    public HippoServices getHippoServices() {
        return retrofit.create(HippoServices.class);
    }
}