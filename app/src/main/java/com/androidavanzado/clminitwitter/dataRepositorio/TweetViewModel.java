package com.androidavanzado.clminitwitter.dataRepositorio;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.androidavanzado.clminitwitter.retrofit.response.Tweet;

import java.util.List;

public class TweetViewModel extends AndroidViewModel {

    public TweetRepocitory tweetRepocitory;                                 // Para tener acceso al los datos que vamos a trae de la webService
    public LiveData<List<Tweet>> tweets;

    public TweetViewModel(@NonNull Application application) {
        super(application);
        tweetRepocitory = new TweetRepocitory();
        tweets = tweetRepocitory.getAllTweets();                            // me traigo los tados del webService y los echo al tweets

    }
    public LiveData<List<Tweet>> getTweets (){                             // en este metodo cuando lo llamen voy a retornar los tweets
        return  tweets;
    }

   /* public LiveData<List<Tweet>> getTweetsOfSwipper (){                    // en este metodo cuando lo llamen voy a retornar los tweets cuando hacemos Swiper
        return  tweets;
    }*/
    public void createtweet(String nuevoTweet){
        tweetRepocitory.createTweet(nuevoTweet);
    }
}
