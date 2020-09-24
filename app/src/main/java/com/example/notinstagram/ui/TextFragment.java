package com.example.notinstagram.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notinstagram.databinding.FragmentTextBinding;

public class TextFragment extends Fragment {
    private FragmentTextBinding binding;
    private String text;

    public TextFragment(String text){
        this.text = text;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTextBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setText(text);
    }

    public void setText(String text){
        binding.textView.setText(text);
    }
}