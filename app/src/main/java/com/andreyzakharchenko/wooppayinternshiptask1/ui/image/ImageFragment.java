package com.andreyzakharchenko.wooppayinternshiptask1.ui.image;

import static com.andreyzakharchenko.wooppayinternshiptask1.R.drawable.ic_baseline_sync_24;
import static com.andreyzakharchenko.wooppayinternshiptask1.R.drawable.ic_image_image_24;

import android.graphics.Bitmap;
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
                imageView.setImageResource(ic_baseline_sync_24);
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
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                imagePresenter.getImage(textInImage);
            }
        });
        thread.start();
    }

    @Override
    public void showImage(Bitmap bitmap) {
        imageView.post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(bitmap);
            }
        });
    }

}