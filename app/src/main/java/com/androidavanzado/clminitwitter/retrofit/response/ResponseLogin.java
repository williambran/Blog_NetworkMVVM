
package com.androidavanzado.clminitwitter.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//Este pojomse creo con la respuesta del login , se hizo copypage a jsonchema2pojo.org y se exporta aqui
public class ResponseLogin {


    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("photoUrl")
    @Expose
    private String photoUrl;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("active")
    @Expose
    private Boolean active;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseLogin() {
    }

    /**
     * 
     * @param photoUrl
     * @param created
     * @param active
     * @param email
     * @param token
     * @param username
     */
    public ResponseLogin(String token, String username, String email, String photoUrl, String created, Boolean active) {
        super();
        this.token = token;
        this.username = username;
        this.email = email;
        this.photoUrl = photoUrl;
        this.created = created;
        this.active = active;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}
