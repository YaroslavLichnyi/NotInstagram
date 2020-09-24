package com.example.notinstagram.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.notinstagram.entities.ContentWrapper;
import com.example.notinstagram.repository.Repository;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ContentViewModel extends AndroidViewModel {
    private static String TAG = ContentViewModel.class.getSimpleName();
    private MutableLiveData<List<ContentWrapper>> contentMutableLiveData;
    private MutableLiveData<String> errorLiveData;
    private Application application;

    public ContentViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        contentMutableLiveData = new MutableLiveData<>();
        errorLiveData = new MutableLiveData<>();
        subscribeOnContentLiveData();
        Repository.getInstance(application).getSmth();
    }

    public MutableLiveData<List<ContentWrapper>> getContentMutableLiveData() {
        return contentMutableLiveData;
    }

    public MutableLiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    private void subscribeOnContentLiveData(){
        Repository
                .getInstance(application)
                .getSmth()
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<List<ContentWrapper>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<ContentWrapper> contentWrappers) {
                        contentMutableLiveData.postValue(contentWrappers);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                        errorLiveData.postValue("Error while getting data from server");
                    }
                });
    }

}