package com.deonnao.luop

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference
    private lateinit var newsList: ArrayList<Article>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.newsRV)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        auth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance().reference
        newsList = ArrayList()

        newsList.add(Article("Langston Renovates the SSC", "By The Gazette"))

        newsList.add(Article("Langston Tulsa Merges Program With OSU", "By The Gazette"))

        newsList.add(Article("Track Team Earns Academic Honors", "By The Gazette"))

        newsList.add(Article("Softball Team Wins First Home Game", "By The Gazette"))

        newsList.add(Article("Langston PT School Named Best in the State", "By The Gazette"))

        newsList.add(Article("Langston Football Team Remains Undefeated", "By The Gazette"))

        newsList.add(Article("Girls Basketball Team Claims Victory in Playoffs", "By The Gazette"))

        newsList.add(Article("Langston Athletics Receives Generous Donation", "By The Gazette"))

        val adapter = NewsAdapter(newsList)
        recyclerView.adapter = adapter

        return view
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {

            }
    }
}