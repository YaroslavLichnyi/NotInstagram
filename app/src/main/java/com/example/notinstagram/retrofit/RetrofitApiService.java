package com.example.notinstagram.retrofit;

import com.example.notinstagram.api.NotInstagramApiService;
import com.example.notinstagram.entities.Content;
import com.example.notinstagram.entities.ContentItem;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiService {
    private Retrofit retrofit;
    public static final String BASE_URL = "https://demo0040494.mockable.io/api/v1/";
    private static RetrofitApiService retrofitApiService;
    private NotInstagramApiService infoDownloader;

    public static RetrofitApiService getInstance(){
        if(retrofitApiService == null){
            retrofitApiService = new RetrofitApiService();
            retrofitApiService.retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            retrofitApiService.infoDownloader = retrofitApiService.retrofit.create(NotInstagramApiService.class);
        }
        return retrofitApiService;
    }


    private RetrofitApiService(){
    }

    public Observable<List<Content>> getContent(){
        return infoDownloader.getContent();
    }

    public Observable<ContentItem> getContentItem(int id){
        return infoDownloader.getContentItem(id);
    }

}
