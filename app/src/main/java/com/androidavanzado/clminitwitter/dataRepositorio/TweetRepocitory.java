package com.androidavanzado.clminitwitter.dataRepositorio;

import android.app.DownloadManager;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.androidavanzado.clminitwitter.common.MyApp;
import com.androidavanzado.clminitwitter.retrofit.AuthTwitterClient;
import com.androidavanzado.clminitwitter.retrofit.AuthTwitterService;
import com.androidavanzado.clminitwitter.retrofit.request.RequesCreateTweet;
import com.androidavanzado.clminitwitter.retrofit.response.Tweet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TweetRepocitory {                                               //Nos traermos lo que va a ser la coneccion a la web service


    //Nos traermos lo que va a ser la coneccion a la web service

    private AuthTwitterService authTwitterService;
    private AuthTwitterClient authTwitterClient;
    private MutableLiveData<List<Tweet>> allTweets;


    public TweetRepocitory() {                                                 // Constructor
        // para traer la instancia del cliente y servicio (interfaz)
        authTwitterClient = AuthTwitterClient.getInstance();
        authTwitterService = authTwitterClient.getAuthTwitterService();
        allTweets = getAllTweets();                                          // cuando haya un cambio en la lista de tweets, el LiveData<List<Tweet>>  notificara al ViewModel que hubo cabios y el observador lo vera, y refrescara el RecyclerView
    }

    public MutableLiveData<List<Tweet>> getAllTweets() {
//        final MutableLiveData<List<Tweet>> data = new MutableLiveData<>();   // variable tipo mutabla, es decir, que puede cambiar con el tiempo, ejemplo , en  data.setValue(response.body)
        if(allTweets == null){                                                   // necesitamos hacer una validacion por que se manda a llamar y esta vacio, igual que en el adapter
            allTweets = new MutableLiveData<>();

        }
        Call<List<Tweet>> call = authTwitterService.getAllTweets();          // Recibimos una lista de Tweet

        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                if (response.isSuccessful()) {
                    allTweets.setValue(response.body());
                } else {
                    Toast.makeText(MyApp.getContext(), "Algo salio mal", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {
                Toast.makeText(MyApp.getContext(), "Algo salio mal con la conexion ", Toast.LENGTH_SHORT).show();
            }
        });
        return allTweets;
    }


    public void createTweet( String mensaje){
        RequesCreateTweet requesCreateTweet = new RequesCreateTweet( mensaje );
        Call<Tweet> call = authTwitterService.createTweet(requesCreateTweet);

        call.enqueue(new Callback<Tweet>() {
            @Override
            public void onResponse(Call<Tweet> call, Response<Tweet> response) {
                if (response.isSuccessful()){
                    List<Tweet> listaClonada = new ArrayList<>();                                          //Si a respuesta es correcta, creamos una lista clonada, y le add el nuevo tweet, que es l respuesta,
                    listaClonada.add(response.body());
                    for (int i = 0; i < allTweets.getValue().size(); i++) {                                // recorremos la lista ya existente
                        listaClonada.add(new Tweet(allTweets.getValue().get(i)));                          // Creamos un constructor en Tweet para crear nuevos, y volvemos a ccrear twwet que teniamos en la antigua lista, con el nuevo tweet ue creamos arriba
                    }
                    allTweets.setValue(listaClonada);
                }
            }

            @Override
            public void onFailure(Call<Tweet> call, Throwable t) {
                Toast.makeText(MyApp.getContext(), "Algo salio mal,Revise la conexion a internet.", Toast.LENGTH_SHORT).show();

            }
        });



    }


}
