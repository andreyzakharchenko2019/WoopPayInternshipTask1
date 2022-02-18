package com.andreyzakharchenko.wooppayinternshiptask1.model;

import static com.andreyzakharchenko.wooppayinternshiptask1.Constants.URL_IMAGE;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class ImageModel {

    private Bitmap bitmap;
    private String textInImage;

    public Bitmap getImage(String textInImage) {
        this.textInImage = textInImage;
        GetImage getImage = new GetImage();
        getImage.start();
        try {
            getImage.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private class GetImage extends Thread {
        @Override
        public void run() {
            try {
                InputStream inputStream = new java.net.URL(URL_IMAGE + textInImage).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
