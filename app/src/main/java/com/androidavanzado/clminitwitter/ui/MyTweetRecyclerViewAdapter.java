package com.androidavanzado.clminitwitter.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.clminitwitter.R;
import com.bumptech.glide.Glide;
import com.androidavanzado.clminitwitter.common.Constantes;
import com.androidavanzado.clminitwitter.common.SharePreferencesManager;
import com.androidavanzado.clminitwitter.retrofit.response.Like;
import com.androidavanzado.clminitwitter.retrofit.response.Tweet;

import java.util.List;


public class MyTweetRecyclerViewAdapter extends RecyclerView.Adapter<MyTweetRecyclerViewAdapter.ViewHolder> {

    private List<Tweet> mValues;
    private Context ctx;
    private String username;

    public MyTweetRecyclerViewAdapter(Context context, List<Tweet> items) {// aqui me envian la lista de item vacia
        mValues = items;
        ctx = context;
        username = SharePreferencesManager.getSometimeStringValue(Constantes.PREF_USERNAME);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tweet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (mValues != null){
            holder.mItem = mValues.get(position);
        holder.tvUsername.setText(holder.mItem.getUser().getUsername());
        holder.tvMessage.setText(holder.mItem.getMensaje());
        holder.tvLikeCount.setText(String.valueOf(holder.mItem.getLikes().size())); // array de likes


        String photo = holder.mItem.getUser().getPhotoUrl();
        if (!photo.equals("")) {
            Glide.with(ctx)
                    .load("https://www.minitwitter.com/apiv1/uploads/photos/" + photo)
                 //   .error(R.drawable.ic_account_circle_black_24dp)
                    .into(holder.ivAvatar);
        } else {
            Glide.with(ctx)
                    .load(R.drawable.ic_account_circle_black_24dp)
                    .into(holder.ivAvatar);
        }
//Like de nosotros
        for (Like like : holder.mItem.getLikes()) {
            if (like.getUsername().equals(username)) {
                Glide.with(ctx)
                        .load(R.drawable.ic_favorite_black_24dp)
                        .into(holder.ivLike);
            } else {
                Glide.with(ctx).load(R.drawable.ic_favorite).into(holder.ivLike);
                break;
            }
        }

    }
}

    @Override
    public int getItemCount() {
        if (mValues!=null){
        return mValues.size();

        }else {
            return 0;
        }
    }
    public void setData(List<Tweet> listTweet){ //aqui es dodne se va a observar, aqui pasamos verdaderamente  pasamos los datos.
        mValues=listTweet;
        notifyDataSetChanged();

    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView ivAvatar;
        public final ImageView ivLike;
        public final TextView tvUsername;
        public final TextView tvMessage;
        public final TextView tvLikeCount;
        public Tweet mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ivAvatar = view.findViewById(R.id.imageViewAvatar);
            ivLike = view.findViewById(R.id.imageViewLike);
            tvUsername = view.findViewById(R.id.textViewUserName);
            tvMessage = view.findViewById(R.id.textViewMesseage);
            tvLikeCount = view.findViewById(R.id.textViewLikes);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvUsername.getText() + "'";
        }
    }


}
