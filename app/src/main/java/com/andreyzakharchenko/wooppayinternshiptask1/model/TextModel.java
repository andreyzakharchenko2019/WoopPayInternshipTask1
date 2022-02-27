package com.andreyzakharchenko.wooppayinternshiptask1.model;

import static com.andreyzakharchenko.wooppayinternshiptask1.Constants.NAME_FIELD_JSON_FACT;
import static com.andreyzakharchenko.wooppayinternshiptask1.Constants.NAME_FIELD_JSON_FACT_TRANSLATE;
import static com.andreyzakharchenko.wooppayinternshiptask1.Constants.URL_API_TRANSLATE;
import static com.andreyzakharchenko.wooppayinternshiptask1.Constants.URL_FACT;

import com.andreyzakharchenko.wooppayinternshiptask1.util.OkHttpSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TextModel {

    private String fact;
    private String translateFact;

    public String getFactAboutCats() {
        OkHttpClient client = OkHttpSingleton.getInstance();
        Request request = new Request.Builder()
                .url(URL_FACT)
                .build();
        try {
            Response response = client.newCall(request).execute();
            parsingFactFromJSON(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fact;
    }

    private void parsingFactFromJSON(String factCatJSON) {
        try {
            JSONObject jsonObject = new JSONObject(factCatJSON);
            fact = jsonObject.getString(NAME_FIELD_JSON_FACT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTranslateFact(String fact) {
        this.fact = fact;
        OkHttpClient client = OkHttpSingleton.getInstance();
        Request request = new Request.Builder()
                .url(URL_API_TRANSLATE + fact)
                // .post(body)
                .addHeader("x-rapidapi-key", "a5e68975c7mshb36f57ca47ccf9bp1da4c3jsnbcdd60f9246b")
                .addHeader("x-rapidapi-host", "just-translated.p.rapidapi.com")
                .build();

        try {
            Response response = client.newCall(request).execute();
            parsingFactTranslateFromJSON(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return translateFact;
    }

    private void parsingFactTranslateFromJSON(String factTranslateJSON) {
        try {
            JSONObject jsonObject = new JSONObject(factTranslateJSON);
            String string = jsonObject.getString(NAME_FIELD_JSON_FACT_TRANSLATE);
            string = string.replace("[\"", "");
            string = string.replace("\"]", "");
            translateFact = string;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
