package com.androidavanzado.clminitwitter.retrofit;

import com.androidavanzado.clminitwitter.common.Constantes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MiniTwitterClient {
    //Clase Singleton

    private static MiniTwitterClient instance = null;
    private  MiniTwitterService miniTwitterService;
    public Retrofit retrofit;

    //Constructor
    public MiniTwitterClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.API_MINITWITTER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        miniTwitterService = retrofit.create(MiniTwitterService.class);
    }

    // Metodo que va a regresar un objeto tipo MiniTwitterClient (patron singletom )
    public static MiniTwitterClient getInstance() {
        if (instance == null) {
            instance = new MiniTwitterClient();
        }
        return instance;
    }

    public MiniTwitterService getminiTwitterService() {
        return miniTwitterService;
    }
}
