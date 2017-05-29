package br.com.enforce.hippov1.rest;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInitializer {

    private final Retrofit retrofit;

    public RetrofitInitializer() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
//        okHttpClient.interceptors().add(new AddCookiesInterceptor(getApplicationContext()));
//        okHttpClient.interceptors().add(new ReceivedCookiesInterceptor(getApplicationContext()));

        retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8080/webservices/")
                .client(okHttpClient.build())
//                .addConverterFactory(JacksonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

//    @Override
    public HippoServices getHippoServices() {
        return retrofit.create(HippoServices.class);
    }
}