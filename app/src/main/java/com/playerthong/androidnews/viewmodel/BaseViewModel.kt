package com.playerthong.androidnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.playerthong.androidnews.utils.ViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


open class BaseViewModel : ViewModel()  {
    val viewState = MutableLiveData<String>().apply {
        value = ViewState.NONE
    }



    // coroutines
    private var viewModelJob = Job()
    private val ioContext = viewModelJob + Dispatchers.IO // background context
    private val uiContext = viewModelJob + Dispatchers.Main // ui context
    protected val ioScope = CoroutineScope(ioContext)
    protected val uiScope = CoroutineScope(uiContext)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}