package com.playerthong.androidnews.adapter.holder

import android.view.View
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil

import androidx.recyclerview.widget.RecyclerView
import com.playerthong.androidnews.databinding.LoadingItemViewBinding


class LoadingViewHolder: RecyclerView.ViewHolder {
    var binding: LoadingItemViewBinding?

    constructor(itemView: View) : super(itemView) {
        binding= DataBindingUtil.bind(itemView)
    }
}