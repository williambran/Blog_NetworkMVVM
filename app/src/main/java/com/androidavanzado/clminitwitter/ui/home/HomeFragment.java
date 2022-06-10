package com.androidavanzado.clminitwitter.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.clminitwitter.ui.MyTweetRecyclerViewAdapter;
import com.androidavanzado.clminitwitter.R;
import com.androidavanzado.clminitwitter.dataRepositorio.TweetViewModel;
import com.androidavanzado.clminitwitter.retrofit.AuthTwitterClient;
import com.androidavanzado.clminitwitter.retrofit.AuthTwitterService;
import com.androidavanzado.clminitwitter.retrofit.response.Tweet;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private RecyclerView recyclerView;
    private MyTweetRecyclerViewAdapter adapter;
    private List<Tweet> tweetList;

    private AuthTwitterService authTwitterService;
    private AuthTwitterClient authTwitterClient;

    private TweetViewModel tweetViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     //   homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_tweet_list, container, false);

       // final TextView textView = root.findViewById(R.id.text_home);
        //homeViewModel.getText().observe(this, new Observer<String>() {
          //  @Override
        /*    public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        tweetViewModel = ViewModelProviders.of(getActivity()).get(TweetViewModel.class);  //para traer informacion de mi clase TweetViewModel

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            adapter = new MyTweetRecyclerViewAdapter(getContext(), tweetList); //Le estamos pasando una lista vacia de tweets, por que au no llegamos a donde le pasamos los valores, 2 lineas abajo y en el Adapter tenemos que poner condiciones

            recyclerView.setAdapter(adapter);
            loadTweetData();

        }
        return view;
    }


     void loadTweetData() {

       tweetViewModel.getTweets().observe(this, new Observer<List<Tweet>>() {          //Que va a a observar ? El cambio que va a ver en el Adapter
           @Override
           public void onChanged(List<Tweet> tweets) {
               System.out.println("recibi un cambio");
               tweetList = tweets;
               adapter.setData(tweetList);


           }
       });
                    adapter = new MyTweetRecyclerViewAdapter(getActivity(), tweetList); // tweetList es la respuesta del servidor
                    recyclerView.setAdapter(adapter);


     tweetViewModel.getTweets().observe(getActivity(), new Observer<List<Tweet>>() {
         @Override
         public void onChanged(List<Tweet> tweets) {

         }
     });

    }
}