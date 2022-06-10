
package com.androidavanzado.clminitwitter.retrofit.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequesCreateTweet {  // No se crea una respuesta pojo por ue es igual a la resuesta del Tweet

    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RequesCreateTweet() {
    }

    /**
     * 
     * @param mensaje
     */
    public RequesCreateTweet(String mensaje) {
        //super();
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
