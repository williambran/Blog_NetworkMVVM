
package com.androidavanzado.clminitwitter.retrofit.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tweet {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("likes")
    @Expose
    private List<Like> likes = null;
    @SerializedName("user")
    @Expose
    private User user;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Tweet() {
    }

    /**
     * 
     * @param id
     * @param mensaje
     * @param user
     * @param likes
     */
    public Tweet(Integer id, String mensaje, List<Like> likes, User user) {
        super();
        this.id = id;
        this.mensaje = mensaje;
        this.likes = likes;
        this.user = user;
    }

    public Tweet(Tweet nuevoTweets) { //esto lo ocupamos en el repocitorio, para crear nuevo twwits apartir de este constructor
        this.id = nuevoTweets.id;
        this.mensaje = nuevoTweets.mensaje;
        this.likes= nuevoTweets.likes;
        this.user = nuevoTweets.user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
