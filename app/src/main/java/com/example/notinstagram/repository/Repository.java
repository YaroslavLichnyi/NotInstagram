package com.example.notinstagram.repository;

import android.content.Context;

import com.example.notinstagram.entities.Content;
import com.example.notinstagram.entities.ContentItem;
import com.example.notinstagram.entities.ContentWrapper;
import com.example.notinstagram.retrofit.RetrofitApiService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class Repository {
    private static Repository instance;
    private Context context;
    private Repository() {
    }

    public static Repository getInstance(Context context){
        if (instance == null){
            instance = new Repository();
            instance.context = context;
        }
        return instance;
    }

    public Observable<List<Content>> getContent() {
        return RetrofitApiService
                .getInstance()
                .getContent();
    }

    public Observable<ContentItem> getContentItem(int id){
        return RetrofitApiService
                .getInstance()
                .getContentItem(id);
    }

    public Single<List<ContentWrapper>> getSmth(){
        return getContent()
                .flatMap(Observable::fromIterable)
                .flatMap(new Function<Content, ObservableSource<ContentWrapper>>() {
                    @Override
                    public ObservableSource<ContentWrapper> apply(Content content) throws Exception {
                        return Repository
                                .getInstance(instance.context)
                                .getContentItem((int)content.getId())
                                .map(new Function<ContentItem, ContentWrapper>() {
                                    @Override
                                    public ContentWrapper apply(ContentItem contentItem) throws Exception {
                                        return new ContentWrapper(content, contentItem);
                                    }
                                });
                    }
                })
                .toList();
    }

}
