package com.example.nesti_mes_recettes;

import android.app.Application;

/**
 * Class pour les variables globales
 * Cette classe est ajoutée au fichier AndroidMAnifest.xml
 * Les variable sont instenciée dans la classe MainActivity
 * Et peuvent être lu dans les autres classes
 */
public class GlobalsVariable extends Application {
    private String urlAPI;
    private  String urlImage;

    public String getUrlAPI() {
        return urlAPI;
    }

    public void setUrlAPI(String urlAPI) {
        this.urlAPI = urlAPI;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
