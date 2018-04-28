package com.app.seriousbambougame.datas;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Valentin on 28/04/2018.
 */

public class Preference {

    private String user;
    private Context context;


    private final static String
            SHARED_PREF_NAME = "com.app.seriousbougame.SHARED_PREF_NAME",
            USER = "com.app.seriousbougame.USER";

    public Preference(Context c, String user){
        this.user = user;
        this.context = c;
    }
    /**
     *  methodes pour la gestion de la clée et sa valeur du profil dans l'application
     *  Récupérer la valeur de la clée
     * @param c --> Context de l'application
     * @return String du profil
     */
    public static String getUser(Context c){
        SharedPreferences prefs = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString(USER, "");
    }

    /**
     *
     * @param c --> Context de l'application
     * @param profil --> Désignation du CP pour le profil
     */
    public static void setUser(Context c, String profil) {
        //On détruit la valeur précédente
        destroyUser( c );
        //On stock la valeur en mode privé
        SharedPreferences prefs = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //On prépare l'édition de la valeur de la clée
        SharedPreferences.Editor editor = prefs.edit();
        //On édite la clée
        editor.putString(USER, profil);
        //On applique les modifications
        editor.apply();
    }

    /**
     * Supprimer la clé et sa valeur des préférences
     * @param c --> Context de l'application
     */
    public static void destroyUser(Context c){
        SharedPreferences prefs = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        //On supprime la clée et ça valeur
        editor.remove(USER);
        editor.apply();
    }
}
