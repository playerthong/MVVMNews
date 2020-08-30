package com.playerthong.androidnews.model

 class NewsApiResponse  {
    var status: String? = null
    var totalResults = 0
    var articles: List<NewsModel>? = null

     constructor(status: String?, totalResults: Int, articles: List<NewsModel>?) {
         this.status = status
         this.totalResults = totalResults
         this.articles = articles
     }
 }