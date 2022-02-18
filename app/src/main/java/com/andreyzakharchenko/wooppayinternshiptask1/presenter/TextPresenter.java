package com.andreyzakharchenko.wooppayinternshiptask1.presenter;

import com.andreyzakharchenko.wooppayinternshiptask1.ui.text.TextView;

public interface TextPresenter {
    void getFactAboutCats();
    void attachView(TextView textView);
    void getTranslateFact(String fact);
    }
