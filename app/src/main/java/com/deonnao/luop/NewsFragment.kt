package com.deonnao.luop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageReference

class NewsFragment : Fragment() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference
    private lateinit var newsList: ArrayList<Article>
    private lateinit var storageRef: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.visibility = View.VISIBLE

        val view = inflater.inflate(R.layout.fragment_news, container, false)
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.newsRV)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        auth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance().reference
        newsList = ArrayList()


        newsList.add(Article("Langston Renovates the SSC", "By The Gazette",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/articleImages%2Flangston_ssc.jpeg?alt=media&token=d4098b57-0f6b-418f-8c08-b541d403237b"))

        newsList.add(Article("Langston Tulsa Merges Program With OSU", "By The Gazette",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/articleImages%2Flangston_tulsa.gif?alt=media&token=eb27175a-d146-4f42-82ba-b41a86e2a882"))

        newsList.add(Article("Track Team Earns Academic Honors", "By The Gazette",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/articleImages%2Flangston_track.png?alt=media&token=90b69f13-0b59-450b-b47e-64ab21ec706e"))

        newsList.add(Article("Softball Team Wins First Home Game", "By The Gazette",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/articleImages%2Flangston_softball.jpeg?alt=media&token=ceadcb53-44a2-4ca1-8c56-b766fb69af75"))

        newsList.add(Article("Langston PT School Named Best in the State", "By The Gazette",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/articleImages%2Flangston_pt_school.webp?alt=media&token=b400ca07-43fc-4a02-afc0-c88f4b4caa3b"))

        newsList.add(Article("Langston Football Team Remains Undefeated", "By The Gazette",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/articleImages%2Flangston_football.jpg?alt=media&token=f46e9019-79b1-470f-9037-568a25e3a1fb"))

        newsList.add(Article("Girls Basketball Team Claims Victory in Playoffs", "By The Gazette",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/articleImages%2Flangston_basketball.png?alt=media&token=c3b5e830-f475-4ed2-8fa1-aff3ec872f66"))

        newsList.add(Article("Langston Athletics Receives Generous Donation", "By The Gazette",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/articleImages%2Flangston_athletics.jpeg?alt=media&token=4aa5413c-6d9d-4549-9fda-28c30c3a8cfe"))

        val adapter = NewsAdapter(newsList)
        recyclerView.adapter = adapter

        return view
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            NewsFragment().apply {

            }
    }
}