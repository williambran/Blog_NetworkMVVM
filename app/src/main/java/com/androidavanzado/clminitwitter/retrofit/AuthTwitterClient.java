package com.androidavanzado.clminitwitter.retrofit;

import com.androidavanzado.clminitwitter.common.Constantes;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthTwitterClient {
    //Clase Singleton
//esta clase es igual que  MiniTwitterClient, pero le agregamos el cliente, por el clinete
    private static AuthTwitterClient instance = null;
    private  AuthTwitterService authTwitterService;
    public Retrofit retrofit;

    //Constructor
    public AuthTwitterClient() {
//        aqui vamos a utilizar el interceptor para adjuntarle el TOKEN a la peticion
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(new AuthInterceptor());
//        Atravez de este configurador creado aca arriba.... hacemos esto
        OkHttpClient client = okHttpClientBuilder.build(); // ahora el cliente ñle podemos asociar informacion atravez dela clase AuthInterceptor a la peticion

        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.API_MINITWITTER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)// aqui vamos a invocar al cliente en todas las peticiones que se hagan , le decirmos que hay un cliente y que traera el token
                .build();
        authTwitterService = retrofit.create(AuthTwitterService.class);
    }

    // Metodo que va a regresar un objeto tipo AuthTwitterClient (patron singletom )
    public static AuthTwitterClient getInstance() {
        if (instance == null) {
            instance = new AuthTwitterClient();
        }
        return instance;
    }

    public AuthTwitterService getAuthTwitterService() {
        return authTwitterService;
    }
}
