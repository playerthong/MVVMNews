package com.playerthong.androidnews.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.playerthong.androidnews.R
import com.playerthong.androidnews.adapter.holder.LoadingViewHolder
import com.playerthong.androidnews.adapter.holder.NewsViewHolder
import com.playerthong.androidnews.model.NewsModel
import com.playerthong.androidnews.utils.Utils
import com.squareup.picasso.Picasso


class NewsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1
    var totalResult = 0

    public lateinit var  newsModelList: List<NewsModel>

    public fun setNewsModelList(newsModelList: List<NewsModel>, totalResult: Int) {
        this.newsModelList = newsModelList
        this.totalResult = totalResult

    }


    @NonNull
    override fun onCreateViewHolder(
        @NonNull parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
         if (viewType == VIEW_TYPE_ITEM) {
            val view: View = LayoutInflater.from(parent.context).inflate(
                R.layout.news_item_view,
                parent,
                false
            )
             return NewsViewHolder(view)
        } else {
            val view: View = LayoutInflater.from(parent.context).inflate(
                R.layout.loading_item_view,
                parent,
                false
            )
             return LoadingViewHolder(view)
        }
    }


    override fun onBindViewHolder(@NonNull holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LoadingViewHolder) {
        } else {
            val newsViewHolder = holder as NewsViewHolder
            val newsModel = newsModelList[position]
            Picasso.get()
                .load(newsModel.urlToImage)
                .placeholder(R.drawable.ic_photo)
                .error(R.drawable.ic_photo)
                .into(newsViewHolder.binding!!.imageView)
            newsViewHolder.binding!!.tvTitle.text = newsModel.title
            newsViewHolder.binding!!.tvDescription.text = newsModel.description
            newsViewHolder.binding!!.tvDate.setText(Utils.getSimpleDate(newsModel.publishedAt))



        }
    }


    override fun getItemViewType(position: Int): Int {
        val total = itemCount
        return if (position == total - 1) {
            if (total == totalResult) {
                VIEW_TYPE_ITEM
            } else {
                VIEW_TYPE_LOADING
            }
        } else {
            VIEW_TYPE_ITEM
        }
    }

    override fun getItemCount(): Int {
        return if (newsModelList == null) {
            0
        } else if (newsModelList!!.size < totalResult) {
            newsModelList!!.size + 1
        } else {
            newsModelList!!.size
        }
    }

    fun beCanLoadingMore(): Boolean {
        return newsModelList != null && itemCount > newsModelList!!.size
    }
}