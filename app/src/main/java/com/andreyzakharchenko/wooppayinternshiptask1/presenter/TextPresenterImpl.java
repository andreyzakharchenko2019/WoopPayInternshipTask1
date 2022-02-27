package com.andreyzakharchenko.wooppayinternshiptask1.presenter;

import com.andreyzakharchenko.wooppayinternshiptask1.model.ContractTextModel;
import com.andreyzakharchenko.wooppayinternshiptask1.ui.text.ContractTextView;

public class TextPresenterImpl implements ContractTextPresenter, ContractTextModel.OnFinishedListenerFact, ContractTextModel.OnFinishedListenerTranslate {

    private ContractTextView textView;
    private ContractTextModel textModel;

    public TextPresenterImpl(ContractTextModel textModel) {
        this.textModel = textModel;
    }

    @Override
    public void attachView(ContractTextView contractTextView) {
        this.textView = contractTextView;
    }

    @Override
    public void getFactAboutCats() {
        textModel.getFactAboutCats(this);
    }

    @Override
    public void onFinishedFact(String factAboutCats) {
        textView.showFactAboutCats(factAboutCats);
        getTranslateFact(factAboutCats);
    }

    private void getTranslateFact(String factAboutCats) {
        textView.translatingFact();
        textModel.getTranslateFact(this, factAboutCats);
    }


    @Override
    public void onFinishedTranslate(String translatedFact) {
        textView.showTranslateFact(translatedFact);
    }
}
