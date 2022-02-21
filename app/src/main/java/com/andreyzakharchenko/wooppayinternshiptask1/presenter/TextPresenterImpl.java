package com.andreyzakharchenko.wooppayinternshiptask1.presenter;

import com.andreyzakharchenko.wooppayinternshiptask1.model.TextModel;
import com.andreyzakharchenko.wooppayinternshiptask1.ui.text.TextView;

public class TextPresenterImpl implements TextPresenter{

    private TextView textView;
    private TextModel textModel;

    public TextPresenterImpl(TextModel textModel) {
        this.textModel = textModel;
    }

    @Override
    public void attachView(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void getFactAboutCats() {
        textView.showFactAboutCats(textModel.getFactAboutCats());
    }

    @Override
    public void getTranslateFact(String fact) {
        textView.showTranslateFact(textModel.getTranslateFact(fact));
    }
}
