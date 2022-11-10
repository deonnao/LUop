package com.deonnao.luop

import android.app.Activity
import android.content.Intent
import android.content.Intent.getIntent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.media.Image
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.NonDisposableHandle.parent


class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        auth = FirebaseAuth.getInstance()

        val profileUsername = view.findViewById<TextView>(R.id.userFullNameTv)
        val profileUserEmail = view.findViewById<TextView>(R.id.userEmailTv)
        val logOutBtn = view.findViewById<Button>(R.id.logOutBtn)
        val profilePic = view.findViewById<ImageView>(R.id.profilePic)

        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.visibility = View.GONE

        val backBtn = view.findViewById<ImageView>(R.id.profileBackButton)

        dbRef = FirebaseDatabase.getInstance().reference.child("user")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.getValue(User::class.java)
                    if(auth.currentUser?.uid == currentUser?.uid) {
                        //userList.add(currentUser!!)
                        val imageUrl = currentUser?.profileImageUrl
                        Picasso.get().load(imageUrl).into(profilePic)
                        val email = currentUser?.email
                        Log.d("profile", email.toString())
                        profileUserEmail.text = email
                        val name = currentUser?.name
                        Log.d("profile", name.toString())
                        profileUsername.text = name
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }

        })

        backBtn.setOnClickListener {
            val fragmentManager = getFragmentManager()
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.frame_layout, MessageFragment())
            fragmentTransaction?.commit()
        }

        logOutBtn.setOnClickListener {
            auth.signOut()
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }


        // Inflate the layout for this fragment
        return view
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
            }
    }
}