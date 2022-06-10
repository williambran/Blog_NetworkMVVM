package com.androidavanzado.clminitwitter.retrofit;

import com.androidavanzado.clminitwitter.retrofit.request.RequesCreateTweet;
import com.androidavanzado.clminitwitter.retrofit.response.Tweet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AuthTwitterService {
    @GET("tweets/all")
    Call<List<Tweet>> getAllTweets();

    @POST("tweets/create")
    Call<Tweet> createTweet(@Body RequesCreateTweet requesCreateTweet);
}
