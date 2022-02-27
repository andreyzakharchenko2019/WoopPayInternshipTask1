package com.andreyzakharchenko.wooppayinternshiptask1.ui.image;

import android.graphics.Bitmap;

public interface ContractImageView {
    void getImage(String textInImage);
    void showImage(String textInImage);
    void showNotEnoughCharacters();
}
