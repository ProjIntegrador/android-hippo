package br.com.enforce.hippov1.rest;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {

    public static final String PREF_COOKIES = "PREF_COOKIES";
    private Context context;

    public ReceivedCookiesInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }


            SharedPreferences sp = context.getSharedPreferences("hippo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();

            editor.putStringSet(PREF_COOKIES, cookies);
            editor.apply();
            editor.commit();

        }

        return originalResponse;
    }
}