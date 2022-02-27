package com.andreyzakharchenko.wooppayinternshiptask1.util;

import okhttp3.OkHttp;
import okhttp3.OkHttpClient;

public class OkHttpSingleton {

    private static OkHttpClient instance;

    private OkHttpSingleton() {
    }

    public static OkHttpClient getInstance() {
        if (instance == null) {
            synchronized (OkHttpSingleton.class) {
                if (instance == null) {
                    instance = new OkHttpClient();
                }
            }
        }
        return instance;
    }


}
