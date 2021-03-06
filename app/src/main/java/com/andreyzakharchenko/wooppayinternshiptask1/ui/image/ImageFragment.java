package com.andreyzakharchenko.wooppayinternshiptask1.ui.image;

import static com.andreyzakharchenko.wooppayinternshiptask1.Constants.*;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.andreyzakharchenko.wooppayinternshiptask1.R;
import com.andreyzakharchenko.wooppayinternshiptask1.databinding.FragmentImageBinding;
import com.andreyzakharchenko.wooppayinternshiptask1.model.ImageModel;
import com.andreyzakharchenko.wooppayinternshiptask1.presenter.ImagePresenterImpl;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class ImageFragment extends Fragment implements ContractImageView {

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
            getImage(editText.getText().toString());
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void getImage(String textInImage) {
        imagePresenter.getImage(textInImage);
    }

    @Override
    public void showNotEnoughCharacters() {
        Toast.makeText(getActivity(), R.string.error_empty_text, Toast.LENGTH_LONG).show();
    }


    @Override
    public void showImage(String textInImage) {
        Glide.with(getActivity())
                .load(URL_IMAGE + textInImage)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.ic_baseline_sync_24)
                .into(imageView);
    }
}