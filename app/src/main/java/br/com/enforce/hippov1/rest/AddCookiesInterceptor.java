package br.com.enforce.hippov1.rest;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This interceptor put all the Cookies in Preferences in the Request.
 * Your implementation on how to get the Preferences MAY VARY.
 */
public class AddCookiesInterceptor implements Interceptor {
    public static final String PREF_COOKIES = "PREF_COOKIES";
    private Context context;

    public AddCookiesInterceptor(Context context) {
        this.context = context;
    } // AddCookiesInterceptor()

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        SharedPreferences sp = context.getSharedPreferences("hippo", Context.MODE_PRIVATE);
        HashSet<String> preferences = (HashSet<String>) sp.getStringSet(PREF_COOKIES, new HashSet<String>());

//        HashSet<String> preferences = (HashSet<String>) PreferenceManager.getDefaultSharedPreferences(context).getStringSet(PREF_COOKIES, new HashSet<String>());
        for (String cookie : preferences) {
            Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
            builder.addHeader("Cookie", cookie);
        } // for

        return chain.proceed(builder.build());
    } // intercept()
} // AddCookiesInterceptor