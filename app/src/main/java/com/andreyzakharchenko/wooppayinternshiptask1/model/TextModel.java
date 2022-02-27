package com.andreyzakharchenko.wooppayinternshiptask1.model;

import static com.andreyzakharchenko.wooppayinternshiptask1.Constants.*;

import androidx.annotation.NonNull;

import com.andreyzakharchenko.wooppayinternshiptask1.util.OkHttpSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TextModel implements ContractTextModel {

    @Override
    public void getFactAboutCats(OnFinishedListenerFact onFinishedListenerFact) {
        OkHttpSingleton okHttpSingleton = OkHttpSingleton.getInstance();
        OkHttpClient client = okHttpSingleton.getClient();
        Request request = new Request.Builder()
                .url(URL_FACT)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                onFinishedListenerFact.onFinishedFact(parsingFactFromJSON(response.body().string()));
            }
        });
    }

    private String parsingFactFromJSON(String factCatJSON) {
        try {
            JSONObject jsonObject = new JSONObject(factCatJSON);
            return jsonObject.getString(NAME_FIELD_JSON_FACT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ERROR_GET_FACT;
    }

    @Override
    public void getTranslateFact(OnFinishedListenerTranslate onFinishedListenerTranslate, String fact) {
        OkHttpSingleton okHttpSingleton = OkHttpSingleton.getInstance();
        OkHttpClient client = okHttpSingleton.getClient();
        Request request = new Request.Builder()
                .url(URL_API_TRANSLATE + fact)
                .addHeader("x-rapidapi-key", "a5e68975c7mshb36f57ca47ccf9bp1da4c3jsnbcdd60f9246b")
                .addHeader("x-rapidapi-host", "just-translated.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                onFinishedListenerTranslate.onFinishedTranslate(parsingFactTranslateFromJSON(response.body().string()));
            }
        });
    }

    private String parsingFactTranslateFromJSON(String factTranslateJSON) {
        try {
            JSONObject jsonObject = new JSONObject(factTranslateJSON);
            String string = jsonObject.getString(NAME_FIELD_JSON_FACT_TRANSLATE);
            string = string.replace("[\"", "");
            string = string.replace("\"]", "");
            return string;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ERROR_TRANSLATE_FACT;
    }
}
