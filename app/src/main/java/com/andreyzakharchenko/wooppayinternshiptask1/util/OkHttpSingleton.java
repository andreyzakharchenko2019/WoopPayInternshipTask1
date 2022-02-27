package com.andreyzakharchenko.wooppayinternshiptask1.util;

import okhttp3.OkHttpClient;

public class OkHttpSingleton {

    private static volatile OkHttpSingleton instance;
    private OkHttpClient client;

    private OkHttpSingleton() {
        client = new OkHttpClient.Builder()
                .build();
    }

    public static OkHttpSingleton getInstance() {
        if (instance == null) {
            synchronized (OkHttpSingleton.class) {
                if (instance == null) {
                    instance = new OkHttpSingleton();
                }
            }
        }
        return instance;
    }

    public OkHttpClient getClient() {
        return client;
    }


}
