package com.androidavanzado.clminitwitter.common;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferencesManager {

    public SharePreferencesManager() {
    }


    private static final String APP_SETTINGS_FILE = "APP_SETTING";

    private static SharedPreferences getSharedPreferences() {                                //es privado por que lo usaremos a nivel de metodos de esta clase, Y ES UN METODO QUE DEVUELVE UN OBJETO TIPO SHAREPREFERENCE

        return MyApp.getContext().getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE);
    }

    //    Metodo que lo mandaremos llamar cuando queramos hacer un sharePreferens
    public static void setSometimeStringValue(String dataLabel, String dataValue) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(dataLabel, dataValue);
        editor.commit();

    }

    public static void setSometimeBooleanValue(String dataLabel, Boolean dataValue) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(dataLabel, dataValue);
        editor.commit();

    }

    //    Metodo para mandar a traer algo del sare preferences , primero parametro con la clave, segundo valor, vamos a devolver en el return algo que no existe
    public static String getSometimeStringValue(String dataLabel) {
        return getSharedPreferences().getString(dataLabel, null); // Le pasamos el valor de la llave y un null por si no encuentra nada que no nos diga nada
    }

    //Lo mismo qe el de arriba pero en Boolean
    public static Boolean getSomeBooleanValue(String dataLabel) {
        return getSharedPreferences().getBoolean(dataLabel, false);
    }

}
