package com.andreyzakharchenko.wooppayinternshiptask1.model;

import static com.andreyzakharchenko.wooppayinternshiptask1.Constants.URL_IMAGE;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class ImageModel {

    private Bitmap bitmap;

    public Bitmap getImage(String textInImage) {
        InputStream inputStream = null;
        try {
            inputStream = new java.net.URL(URL_IMAGE + textInImage).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }
}
