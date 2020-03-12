package com.example.myproject.network;

import com.example.myproject.model.MoviesResponse;
import com.example.myproject.model.NewsResponse;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

//    @GET("public-api/v1.4/movies")
//    Observable<MoviesResponse> getMovies(@Query("page_size") int pageSize, @Query("moviesFields") String fields);

    @GET("public-api/v1.4/movies")
//    Observable<MoviesResponse> getMovies(@Query("page_size") int pageSize, @Query("fields") String field);
    Observable<MoviesResponse> getMovies(@Query("fields") String field);

    @GET("/public-api/v1.4/news/")
    Observable<NewsResponse> getNews(@Query("fields") String fields, @Query("text_format") String textFormat);
}
