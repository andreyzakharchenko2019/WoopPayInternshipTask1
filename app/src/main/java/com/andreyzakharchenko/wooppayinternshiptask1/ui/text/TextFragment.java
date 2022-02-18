package com.andreyzakharchenko.wooppayinternshiptask1.ui.text;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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
            getFactAboutCats();
            getTranslateFact();
            //new GetFact().start();
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
        textPresenter.getFactAboutCats();
    }

    @Override
    public void showFactAboutCats(String fact) {
        textFactView.setText(fact);
    }

    @Override
    public void searchingFact() {
        textFactView.setText(R.string.search_fact);
    }

    @Override
    public void getTranslateFact() {
        textPresenter.getTranslateFact(textFactView.getText().toString());
    }

    @Override
    public void showTranslateFact(String translateFact) {
        textTranslateView.setText(translateFact);
    }
}