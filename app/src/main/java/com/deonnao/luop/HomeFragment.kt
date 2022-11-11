package com.deonnao.luop

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsListAnnounce: ArrayList<Article>
    private lateinit var postList: ArrayList<Post>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.visibility = View.VISIBLE

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView = view.findViewById(R.id.announcmentsRv)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        newsListAnnounce = ArrayList()

        newsListAnnounce.add(Article("Langston Renovates the SSC", "By The Gazette",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/articleImages%2Flangston_ssc.jpeg?alt=media&token=d4098b57-0f6b-418f-8c08-b541d403237b"))

        newsListAnnounce.add(Article("Langston Tulsa Merges Program With OSU", "By The Gazette",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/articleImages%2Flangston_tulsa.gif?alt=media&token=eb27175a-d146-4f42-82ba-b41a86e2a882"))

        newsListAnnounce.add(Article("Track Team Earns Academic Honors", "By The Gazette",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/articleImages%2Flangston_track.png?alt=media&token=90b69f13-0b59-450b-b47e-64ab21ec706e"))

        newsListAnnounce.add(Article("Softball Team Wins First Home Game", "By The Gazette",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/articleImages%2Flangston_softball.jpeg?alt=media&token=ceadcb53-44a2-4ca1-8c56-b766fb69af75"))

        newsListAnnounce.add(Article("Langston PT School Named Best in the State", "By The Gazette",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/articleImages%2Flangston_pt_school.webp?alt=media&token=b400ca07-43fc-4a02-afc0-c88f4b4caa3b"))

        newsListAnnounce.add(Article("Langston Football Team Remains Undefeated", "By The Gazette",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/articleImages%2Flangston_football.jpg?alt=media&token=f46e9019-79b1-470f-9037-568a25e3a1fb"))

        newsListAnnounce.add(Article("Girls Basketball Team Claims Victory in Playoffs", "By The Gazette",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/articleImages%2Flangston_basketball.png?alt=media&token=c3b5e830-f475-4ed2-8fa1-aff3ec872f66"))

        newsListAnnounce.add(Article("Langston Athletics Receives Generous Donation", "By The Gazette",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/articleImages%2Flangston_athletics.jpeg?alt=media&token=4aa5413c-6d9d-4549-9fda-28c30c3a8cfe"))

        val adapter = AnnouncementsAdapter(newsListAnnounce)
        recyclerView.adapter = adapter



        val layoutManager2 = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.postsRv)
        recyclerView.layoutManager = layoutManager2
        recyclerView.setHasFixedSize(true)
        postList = ArrayList()


        postList.add(
            Post("That Girl", "I am THAT GIRL",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/postsProfileImages%2Fblack_girl_headshot_9.webp?alt=media&token=e186cf0d-2273-4e88-ace2-ab3dae77e6ce")
        )

        postList.add(
            Post("Keisha", "Langston is the best!",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/postsProfileImages%2Fblack_girl_headshot_10.jpeg?alt=media&token=276f1972-9cd7-45bb-8298-e5a1f1338895")
        )

        postList.add(
            Post("Rayvyn", "What is everyone up to?",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/postsProfileImages%2Fblack_girl_headshot_16.jpeg?alt=media&token=94a6de4e-5057-454c-a6af-d074fee7e31c")
        )

        postList.add(
            Post("Kels", "I'm bored! Someone host an event",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/postsProfileImages%2Fblack_girl_headshot_11.jpeg?alt=media&token=0fe2f810-af0d-483b-b23e-1aca8fbda03d")
        )

        postList.add(
            Post("Haley", "WOP is hosting an event today in the SSC",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/postsProfileImages%2Fblack_girl_headshot_12.webp?alt=media&token=6f4d41ef-1e69-4866-8b87-e04b0539c9f6")
        )

        postList.add(
            Post("Jenna", "Don't forget to protect your mental health",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/postsProfileImages%2Fblack_girl_headshot_13.jpeg?alt=media&token=5cdc890a-387a-4751-af67-9eb214432b47")
        )

        postList.add(
            Post("Kaylin", "Don't forget to vote!",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/postsProfileImages%2Fblack_girl_headshot_15.jpeg?alt=media&token=e18b393c-f78b-405f-b157-676074d3745d")
        )

        postList.add(Post("Kim", "NCNW will host a raffle today in the SSC",
            "https://firebasestorage.googleapis.com/v0/b/luop-412ca.appspot.com/o/postsProfileImages%2Fblack_girl_headshot_14.jpeg?alt=media&token=b3942a31-fc26-43be-af26-9e0ae562531e"))

        val adapter2 = PostAdapter(postList)
        recyclerView.adapter = adapter2

        return view
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {

            }
    }
}