package com.example.notinstagram.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.example.notinstagram.viewmodel.ContentViewModel;
import com.example.notinstagram.R;
import com.example.notinstagram.entities.ContentItem;
import com.example.notinstagram.entities.ContentWrapper;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ContentViewModel viewModel;
    private List<ContentWrapper> content;
    private int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(ContentViewModel.class);
        subscribeOnLiveData();
    }

    private void subscribeOnLiveData(){
        viewModel
                .getContentMutableLiveData()
                .observe(this, new Observer<List<ContentWrapper>>() {
                    @Override
                    public void onChanged(List<ContentWrapper> contentWrappers) {
                        content = contentWrappers;
                        setFragmentOnPage(0);
                    }
                });
        viewModel
                .getErrorLiveData()
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext()).create();
                        alertDialog.setTitle("Attention");
                        alertDialog.setMessage(s);
                        alertDialog.show();
                    }
                });
    }

    private void setFragmentOnPage(int number){
        Fragment fragment = null;
        ContentItem contentItem = content.get(number).getContentItem();
        switch (contentItem.getType()){
            case "text":
                fragment = new TextFragment(contentItem.getContents());
                break;
            case "webview":
                fragment = new WebViewFragment(contentItem.getUrl());
                break;
            case "game":
                fragment = new Fragment();
                break;

        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLay, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void onClick(View v) {
        if (content != null){
            if (currentItem == content.size() - 1 ){
                currentItem = 0;
            } else {
                currentItem++;
            }
            setFragmentOnPage(currentItem);
        }
    }

}