package com.andreyzakharchenko.wooppayinternshiptask1.ui.text;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.andreyzakharchenko.wooppayinternshiptask1.R;
import com.andreyzakharchenko.wooppayinternshiptask1.databinding.FragmentTextBinding;
import com.andreyzakharchenko.wooppayinternshiptask1.model.TextModel;
import com.andreyzakharchenko.wooppayinternshiptask1.presenter.TextPresenter;
import com.andreyzakharchenko.wooppayinternshiptask1.presenter.TextPresenterImpl;

public class TextFragment extends Fragment implements com.andreyzakharchenko.wooppayinternshiptask1.ui.text.TextView {

    private FragmentTextBinding binding;

    private TextPresenter textPresenter;

    private TextView textFactView;
    private TextView textTranslateView;

    private Handler handler = new Handler(Looper.getMainLooper());

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTextBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button button = binding.factButton;
        textFactView = binding.factCatText;
        textTranslateView = binding.translateFactCatText;

        TextModel textModel = new TextModel();
        textPresenter = new TextPresenterImpl(textModel);
        textPresenter.attachView(this);

        button.setOnClickListener(view -> {
            textFactView.setText(R.string.search_fact);
            getFactAboutCats();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void getFactAboutCats() {
        Thread thread = new Thread(() -> textPresenter.getFactAboutCats());
        thread.start();
    }

    @Override
    public void showFactAboutCats(String fact) {
        textFactView.post(() -> textFactView.setText(fact));
        handler.post(new Runnable() {
            @Override
            public void run() {
                getTranslateFact();
            }
        });
    }

    @Override
    public void getTranslateFact() {
        textTranslateView.setText(R.string.search_translate);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                textPresenter.getTranslateFact(textFactView.getText().toString());
            }
        });
        thread.start();
    }

    @Override
    public void showTranslateFact(String translateFact) {
        textTranslateView.post(new Runnable() {
            @Override
            public void run() {
                textTranslateView.setText(translateFact);
            }
        });
    }
}