package com.deonnao.luop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NewsFragment : Fragment() {

    private val articles = mutableListOf<Article>()
    lateinit var articlesRecyclerView: RecyclerView
    lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_news, container, false)

        val layoutManager = LinearLayoutManager(context)
        articlesRecyclerView = view.findViewById(R.id.articlesRv)
        articlesRecyclerView.layoutManager = layoutManager
        articlesRecyclerView.setHasFixedSize(true)
        adapter = NewsAdapter(articles)
        articlesRecyclerView.adapter = adapter
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        return view
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            NewsFragment().apply {

            }
    }
}