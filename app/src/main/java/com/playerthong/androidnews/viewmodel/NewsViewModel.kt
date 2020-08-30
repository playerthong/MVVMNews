package com.playerthong.androidnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.playerthong.androidnews.api.ApiResponse
import com.playerthong.androidnews.model.NewsApiResponse
import com.playerthong.androidnews.model.NewsModel
import com.playerthong.androidnews.repository.NewsRepository
import com.playerthong.androidnews.utils.ViewState
import kotlinx.coroutines.launch

class NewsViewModel : BaseViewModel() {
    val repository:NewsRepository=NewsRepository()

    val listNews = MutableLiveData<List<NewsModel>>()







    fun getListNews(page:Int){
        viewState.postValue(ViewState.LOADING)
        ioScope.launch {
             repository.getNews(page).apply {
                 if(this.isSuccess){
                     val result: List<NewsModel>? = this.data?.articles;
                     listNews.postValue(result);
                     viewState.postValue(ViewState.NONE)
                 }else{
                     //TODO implement process fail case
                 }
            };

        }
    }
}
