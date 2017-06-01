package br.com.enforce.hippov1.rest;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInitializer {

    private final Retrofit retrofit;

    public RetrofitInitializer() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://191.252.61.93:8080/webservices/")
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public RetrofitInitializer(Context applicationContext) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.interceptors().add(new AddCookiesInterceptor(applicationContext));
        okHttpClient.interceptors().add(new ReceivedCookiesInterceptor(applicationContext));

        retrofit = new Retrofit.Builder()
                .baseUrl("http://191.252.61.93:8080/webservices/")
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