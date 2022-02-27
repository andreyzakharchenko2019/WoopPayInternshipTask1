package com.andreyzakharchenko.wooppayinternshiptask1.model;

public interface ContractTextModel {
    void getFactAboutCats(OnFinishedListenerFact onFinishedListenerFact);
    interface OnFinishedListenerFact {
        void onFinishedFact(String fact);
    }
    void getTranslateFact(OnFinishedListenerTranslate onFinishedListenerTranslate, String fact);
    interface OnFinishedListenerTranslate {
        void onFinishedTranslate(String translatedFact);
    }
}
