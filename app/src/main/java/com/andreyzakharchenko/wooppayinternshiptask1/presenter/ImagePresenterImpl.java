package com.andreyzakharchenko.wooppayinternshiptask1.presenter;

import com.andreyzakharchenko.wooppayinternshiptask1.model.ImageModel;
import com.andreyzakharchenko.wooppayinternshiptask1.ui.image.ImageView;

public class ImagePresenterImpl implements ImagePresenter {

    private ImageView imageView;
    private ImageModel imageModel;

    public ImagePresenterImpl(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    @Override
    public void attachView(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public void getImage(String textInImage) {
        imageView.showImage(imageModel.getImage(textInImage));
    }


}
