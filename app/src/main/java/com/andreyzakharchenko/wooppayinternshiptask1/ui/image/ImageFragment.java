package com.andreyzakharchenko.wooppayinternshiptask1.ui.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.andreyzakharchenko.wooppayinternshiptask1.R;
import com.andreyzakharchenko.wooppayinternshiptask1.databinding.FragmentImageBinding;
import com.andreyzakharchenko.wooppayinternshiptask1.model.ImageModel;
import com.andreyzakharchenko.wooppayinternshiptask1.model.TextModel;
import com.andreyzakharchenko.wooppayinternshiptask1.presenter.ImagePresenterImpl;
import com.andreyzakharchenko.wooppayinternshiptask1.presenter.TextPresenterImpl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class ImageFragment extends Fragment implements com.andreyzakharchenko.wooppayinternshiptask1.ui.image.ImageView {

    private FragmentImageBinding binding;

    private ImageView imageView;

    private ImagePresenterImpl imagePresenter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentImageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final EditText editText = binding.editText;
        final Button button = binding.mainButton;
        imageView = binding.imageView;

        ImageModel imageModel = new ImageModel();
        imagePresenter = new ImagePresenterImpl(imageModel);
        imagePresenter.attachView(this);

        button.setOnClickListener(view -> {
            if (editText.getText().toString().trim().equals("")) {
                Toast.makeText(getActivity(), R.string.error_empty_text, Toast.LENGTH_LONG).show();
            } else {
                getImage(editText.getText().toString());
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void getImage(String textInImage){
        imagePresenter.getImage(textInImage);
    }

    @Override
    public void showImage(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

}