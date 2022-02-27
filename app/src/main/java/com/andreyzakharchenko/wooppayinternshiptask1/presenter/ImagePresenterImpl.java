package com.andreyzakharchenko.wooppayinternshiptask1.presenter;

import static com.andreyzakharchenko.wooppayinternshiptask1.R.drawable.ic_baseline_sync_24;

import android.widget.Toast;

import com.andreyzakharchenko.wooppayinternshiptask1.R;
import com.andreyzakharchenko.wooppayinternshiptask1.model.ImageModel;
import com.andreyzakharchenko.wooppayinternshiptask1.ui.image.ContractImageView;

public class ImagePresenterImpl implements ContractImagePresenter {

    private ContractImageView imageView;
    private ImageModel imageModel;

    public ImagePresenterImpl(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    @Override
    public void attachView(ContractImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public void getImage(String textInImage) {
        if (textInImage.trim().equals("")) {
            imageView.showNotEnoughCharacters();
        } else {
            imageView.showImage(textInImage);
        }
    }
}
