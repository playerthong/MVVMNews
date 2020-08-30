package com.playerthong.androidnews.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.playerthong.androidnews.R
import com.playerthong.androidnews.adapter.NewsAdapter
import com.playerthong.androidnews.databinding.FragmentNewsBinding
import com.playerthong.androidnews.utils.ViewState
import com.playerthong.androidnews.viewmodel.NewsViewModel


class NewsFragment : Fragment() {

    companion object {
        fun newInstance() = NewsFragment()
    }
    lateinit var binding: FragmentNewsBinding;
    private lateinit var viewModel: NewsViewModel
    var newsAdapter: NewsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root:View= inflater.inflate(R.layout.fragment_news, container, false)
        binding = DataBindingUtil.bind<FragmentNewsBinding>(root)!!
        binding.rcNews.setLayoutManager(LinearLayoutManager(activity))
        val dividerItemDecoration = DividerItemDecoration(
            binding.rcNews.context,
            DividerItemDecoration.HORIZONTAL
        )
        binding.rcNews.addItemDecoration(dividerItemDecoration)

        binding.swRefresh.setOnRefreshListener { //Clear cache


        }
        return  root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        registerListener()
        viewModel.getListNews(0);
    }

    private fun registerListener(){
        //Create listener about state of view
        viewModel.viewState.observe(viewLifecycleOwner) {
            if (it == ViewState.REFRESH || it==ViewState.LOADING) {
                binding.swRefresh.isRefreshing=true
            } else if (it == ViewState.NONE){
                binding.swRefresh.isRefreshing=false
            }
        }

        viewModel.listNews.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()){
                if (newsAdapter == null) {
                    newsAdapter = NewsAdapter()
                    binding.rcNews.adapter = newsAdapter
                    newsAdapter?.setNewsModelList(it,90)
                } else {
                    //TODO implement later
                }
            }
        }
    }

}
