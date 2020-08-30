package com.playerthong.androidnews.adapter.holder

import android.view.View
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.playerthong.androidnews.databinding.NewsItemViewBinding


class NewsViewHolder : RecyclerView.ViewHolder {
    var binding: NewsItemViewBinding? = null

    constructor(itemView: View) : super(itemView) {
        binding= DataBindingUtil.bind(itemView)
    }

}