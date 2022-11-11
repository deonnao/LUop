package com.deonnao.luop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PostAdapter(private val posts: List<Post>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.home_messages, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Get the data model based on position
        val allPosts = posts[position]
        holder.username.text = allPosts.username
        //holder.body.text = allArticles.body
        holder.textPost.text = allPosts.textPost
        Picasso.get().load(allPosts.imageUrl).into(holder.itemView.findViewById<ImageView>(R.id.profilePost))

    }

    override fun getItemCount(): Int {
        return posts.size
    }


    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //store references to elements in our layout view
        val username : TextView
        //val body : TextView
        val textPost : TextView
        val imagePost : ImageView


        //We also create a constructor that accepts the entire item row
        //and does the view lookups to find each sub-view
        init {
            username = itemView.findViewById(R.id.userNamePost)
            //body = itemView.findViewById(R.id.articleBody)
            textPost = itemView.findViewById(R.id.userPostText)
            imagePost = itemView.findViewById(R.id.profilePost)

        }

    }
}