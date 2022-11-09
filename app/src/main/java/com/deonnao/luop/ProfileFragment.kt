package com.deonnao.luop

import android.app.Activity
import android.content.Intent
import android.content.Intent.getIntent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.NonDisposableHandle.parent


class ProfileFragment : Fragment() {

    var pickedPhoto : Uri? = null
    var pickedBitMap : Bitmap? = null
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
        dbRef = FirebaseDatabase.getInstance().reference
        val username = view.findViewById<TextView>(R.id.userFullNameTv)


        // Inflate the layout for this fragment
        return view
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
            }
    }
}