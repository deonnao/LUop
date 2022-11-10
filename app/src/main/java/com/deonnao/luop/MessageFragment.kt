package com.deonnao.luop

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item


class MessageFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.visibility = View.VISIBLE

        val view = inflater.inflate(R.layout.fragment_message, container, false)

        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.messageRV)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        auth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance().reference

        dbRef.child("user").addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val adapter = GroupAdapter<GroupieViewHolder>()
                //userList.clear()
                for(postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.getValue(User::class.java)
                    if(auth.currentUser?.uid != currentUser?.uid) {
                        //userList.add(currentUser!!)
                        adapter.add(UserItem(currentUser!!))
                    }

                }
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })

        // Inflate the layout for this fragment
        return view
    }

    class UserItem(val user: User): Item<GroupieViewHolder>() {
        override fun bind(viewHolder: GroupieViewHolder, position: Int) {
            viewHolder.itemView.findViewById<TextView>(R.id.username).text = user.name
            Picasso.get().load(user.profileImageUrl).into(viewHolder.itemView.findViewById<ImageView>(R.id.userProfileImage))

            viewHolder.itemView.setOnClickListener {
                val intent = Intent(it.context, ChatActivity::class.java)
                intent.putExtra("name", user.name)
                intent.putExtra("uid", user.uid)
                intent.putExtra("pic", user.profileImageUrl)
                it.context?.startActivity(intent)
            }
        }
        override fun getLayout(): Int {
            return R.layout.user_layout
        }
    }

    companion object {
        @JvmStatic fun newInstance(param1: String, param2: String) =
                MessageFragment().apply {

                }
    }
}