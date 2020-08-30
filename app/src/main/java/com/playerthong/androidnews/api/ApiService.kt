package com.playerthong.androidnews.api


import com.playerthong.androidnews.BuildConfig
import com.playerthong.androidnews.model.NewsApiResponse
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;



public interface ApiService {

    @GET("top-headlines")
    fun getTopHeadLines(
        @Query(BuildConfig.API_KEY) apiKey: String?,
        @Query(BuildConfig.API_COUNTRY) country: String?,
        @Query("page") page: Int
    ): Call<NewsApiResponse?>?
}