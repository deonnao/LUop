package com.deonnao.luop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


class MessageAdapter(val context: Context, val messageList: ArrayList<Message>, val receiverProfileUrl: String): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECEIVE = 1
    val ITEM_SENT = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //inflate layout based on receive or sent
        if(viewType == 1) {
            //inflate receive layout
            val view: View = LayoutInflater.from(context).inflate(R.layout.receive,parent,false)
            return ReceiveViewHolder(view)
        }
        else {
            //inflate sent layout
            val view: View = LayoutInflater.from(context).inflate(R.layout.send,parent,false)
            return SentViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //the current view holder is sent view holder
        val currentMessage = messageList[position]
        if(holder.javaClass == SentViewHolder::class.java) {
            //for sent view holder
            val viewHolder = holder as SentViewHolder //type cast
            viewHolder.sentMessage.text = currentMessage.message
        }
        else {
            //for the receive view holder
            val viewHolder = holder as ReceiveViewHolder
            viewHolder.receiveMessage.text = currentMessage.message
            Picasso.get().load(receiverProfileUrl).into(viewHolder.receiverPic)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]

        /*The current user id is equal to the sender id. That means we need to show
        the send view holder*/
        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)) {
            return ITEM_SENT
        }
        else {
            return ITEM_RECEIVE
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    class SentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sentMessage = itemView.findViewById<TextView>(R.id.sentTxt)
    }

    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val receiveMessage = itemView.findViewById<TextView>(R.id.receiveTxt)
        val receiverPic = itemView.findViewById<ImageView>(R.id.receiverPic)
    }

}