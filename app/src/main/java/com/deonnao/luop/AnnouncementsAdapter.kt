package com.deonnao.luop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class AnnouncementsAdapter(private val articles: List<Article>) :
    RecyclerView.Adapter<AnnouncementsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.announcements_layout, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Get the data model based on position
        val allArticles = articles[position]
        holder.titleAnnounce.text = allArticles.title
        //holder.body.text = allArticles.body
        holder.authorAnnounce.text = allArticles.author
        Picasso.get().load(allArticles.imageUrl).into(holder.itemView.findViewById<ImageView>(R.id.newsImageAnnounceIV))

    }

    override fun getItemCount(): Int {
        return articles.size
    }


    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //store references to elements in our layout view
        val titleAnnounce : TextView
        //val body : TextView
        val authorAnnounce : TextView
        val imageAnnounce : ImageView


        //We also create a constructor that accepts the entire item row
        //and does the view lookups to find each sub-view
        init {
            titleAnnounce = itemView.findViewById(R.id.titleAnnounceTv)
            //body = itemView.findViewById(R.id.articleBody)
            authorAnnounce = itemView.findViewById(R.id.sourceAnnounceTv)
            imageAnnounce = itemView.findViewById(R.id.newsImageAnnounceIV)

        }

    }
}