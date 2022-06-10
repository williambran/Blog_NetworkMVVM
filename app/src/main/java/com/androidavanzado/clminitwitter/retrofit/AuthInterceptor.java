package com.androidavanzado.clminitwitter.retrofit;

import com.androidavanzado.clminitwitter.common.Constantes;
import com.androidavanzado.clminitwitter.common.SharePreferencesManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

//    La intefaz viene con este metodo. va a intercepta la peticion y la respuesta
    @Override
    public Response intercept(Chain chain) throws IOException {
        String token= SharePreferencesManager.getSometimeStringValue(Constantes.PREF_TOKEN);
        Request request= chain.request().newBuilder().addHeader("Authorization","Bearer "+token).build(); // para agragar cabecera, y llevar el toquen en vcada peticion que requiera el token

        return chain.proceed(request);
    }
}
