package com.andreyzakharchenko.wooppayinternshiptask1.presenter;

import com.andreyzakharchenko.wooppayinternshiptask1.ui.image.ContractImageView;

public interface ContractImagePresenter {
    void getImage(String textInImage);
    void attachView(ContractImageView imageView);
}
