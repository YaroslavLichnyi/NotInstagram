package com.example.notinstagram.api;

import com.example.notinstagram.entities.Content;
import com.example.notinstagram.entities.ContentItem;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NotInstagramApiService {

    @GET("trending")
    Observable<List<Content>> getContent();

    @GET("object/{id}")
    Observable<ContentItem> getContentItem(@Path("id") int id);
}
