package com.deonnao.luop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NewsAdapter(private val articles: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.news_layout, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Get the data model based on position
        val allArticles = articles[position]
        holder.title.text = allArticles.title
        //holder.body.text = allArticles.body
        holder.author.text = allArticles.author
        Picasso.get().load(allArticles.imageUrl).into(holder.itemView.findViewById<ImageView>(R.id.newsImageIV))
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //store references to elements in our layout view
        val title : TextView
        //val body : TextView
        val author : TextView
        val image : ImageView

        //We also create a constructor that accepts the entire item row
        //and does the view lookups to find each sub-view
        init {
            title = itemView.findViewById(R.id.titleTv)
            //body = itemView.findViewById(R.id.articleBody)
            author = itemView.findViewById(R.id.sourceTv)
            image = itemView.findViewById(R.id.newsImageIV)
        }
    }
}