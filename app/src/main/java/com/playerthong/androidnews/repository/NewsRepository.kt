package com.playerthong.androidnews.repository

import com.playerthong.androidnews.BuildConfig
import com.playerthong.androidnews.api.ApiResponse
import com.playerthong.androidnews.api.ApiResponse.Companion.ERROR_NULL
import com.playerthong.androidnews.api.ApiRetrofit
import com.playerthong.androidnews.api.ApiService
import com.playerthong.androidnews.model.NewsApiResponse
import com.playerthong.androidnews.model.NewsModel
import retrofit2.Call;
import retrofit2.Response;

class NewsRepository {
    var listNewsCache: List<NewsModel>? = null;
    var pageCache:Int=0;
    var status: String? = null
    var totalResults = 0

    fun getNews(page:Int) :ApiResponse<NewsApiResponse>  {
        if(pageCache==page && listNewsCache!=null){
            return ApiResponse<NewsApiResponse>(isSuccess = true, data = NewsApiResponse(status,totalResults,listNewsCache));
        }
       val response: Response<NewsApiResponse?>? =  ApiRetrofit.getService()?.getTopHeadLines(BuildConfig.API_KEY_VALUE,
            BuildConfig.API_COUNTRY_VALUE,page)?.execute();
        if (response != null) {
            if(response.isSuccessful){
                response.body()?.let {
                    return ApiResponse<NewsApiResponse>(isSuccess = true, data = response.body())
                } ?: run {
                    return ApiResponse<NewsApiResponse>(isSuccess = true, data = null)
                }
            }else{
                return ApiResponse.ERROR<NewsApiResponse>(
                    errorCode =ERROR_NULL
                )
            }
        }else{
            return ApiResponse.ERROR<NewsApiResponse>(
                errorCode =ERROR_NULL
            )
        }
    }
}