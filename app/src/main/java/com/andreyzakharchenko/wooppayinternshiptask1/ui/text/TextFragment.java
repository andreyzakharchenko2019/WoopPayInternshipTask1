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
import com.andreyzakharchenko.wooppayinternshiptask1.model.ContractTextModel;
import com.andreyzakharchenko.wooppayinternshiptask1.model.TextModel;
import com.andreyzakharchenko.wooppayinternshiptask1.presenter.ContractTextPresenter;
import com.andreyzakharchenko.wooppayinternshiptask1.presenter.TextPresenterImpl;

public class TextFragment extends Fragment implements ContractTextView {

    private FragmentTextBinding binding;

    private ContractTextPresenter textPresenter;

    private TextView textViewFact;
    private TextView textViewTranslate;

    private Handler handler = new Handler(Looper.getMainLooper());

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTextBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button button = binding.factButton;
        textViewFact = binding.factCatText;
        textViewTranslate = binding.translateFactCatText;

        ContractTextModel textModel = new TextModel();
        textPresenter = new TextPresenterImpl(textModel);
        textPresenter.attachView(this);

        button.setOnClickListener(view -> {
            textViewFact.setText(R.string.search_fact);
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
        textPresenter.getFactAboutCats();
    }

    @Override
    public void showFactAboutCats(String fact) {
        textViewFact.post(() -> textViewFact.setText(fact));
    }

    @Override
    public void translatingFact() {
        textViewTranslate.post(() -> textViewTranslate.setText(R.string.search_translate));
    }

    @Override
    public void showTranslateFact(String translateFact) {
        textViewTranslate.post(() -> textViewTranslate.setText(translateFact));
    }
}