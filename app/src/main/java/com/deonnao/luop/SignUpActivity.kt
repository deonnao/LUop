package com.deonnao.luop

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.getBitmap
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.util.*

const val TAG2 = "SignUpActivity"
class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference
    private var pickedPhoto : Uri? = null
    var pickedBitMap : Bitmap? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        // Initialize Firebase Auth
        auth = Firebase.auth

        val profileImage = findViewById<ImageView>(R.id.profileImage)

        profileImage.setOnClickListener {
            pickedPhoto()
        }

        val etNameSignUp = findViewById<EditText>(R.id.etStudentNameSignUp)
        val etEmailSignUp = findViewById<EditText>(R.id.etEmailSignUp)
        val etPasswordSignUp = findViewById<EditText>(R.id.etPasswordSignUp)

        val signUpBtn = findViewById<Button>(R.id.signUpBtn)

        signUpBtn.setOnClickListener {
            val email = etEmailSignUp.text.toString()
            val password = etPasswordSignUp.text.toString()
            val name = etNameSignUp.text.toString()
            signUp(name, email, password)
        }

    }

    private fun signUp(name: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG2, "createUserWithEmail:success")
                    uploadImageToFirebaseStorage(name,email)
                    //val user = auth.currentUser
                    Toast.makeText(this, "Successfully signed up!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainPage::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG2, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }


    fun pickedPhoto(){
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                1)
        } else {
            // MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent,2)
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (grantResults.size > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galleryIntent,2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val profileImage = findViewById<ImageView>(R.id.profileImage)
        val addImageTV = findViewById<TextView>(R.id.addImageTV)
        pickedPhoto = data?.data
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            if (pickedPhoto != null) {
                addImageTV.alpha = 0F
                if (Build.VERSION.SDK_INT >= 28) {
                    val source = ImageDecoder.createSource(this.contentResolver,pickedPhoto!!)
                    pickedBitMap = ImageDecoder.decodeBitmap(source)
                    profileImage.setImageBitmap(pickedBitMap)
                }
                else {
                    pickedBitMap = MediaStore.Images.Media.getBitmap(this.contentResolver,pickedPhoto)
                    profileImage.setImageBitmap(pickedBitMap)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun addUserToDatabase(name: String, email: String, uid: String, profileImageUrl : String) {
        dbRef = FirebaseDatabase.getInstance().reference
        dbRef.child("user").child(uid).setValue(User(name,email,uid, profileImageUrl))
    }

    private fun uploadImageToFirebaseStorage(name: String, email: String) {

        if(pickedPhoto == null) return
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")
        ref.putFile(pickedPhoto!!)
            .addOnSuccessListener {
                Log.d("SignUpActivity", "${it.metadata?.path}")
                ref.downloadUrl.addOnSuccessListener {
                    Log.d(TAG2, "File location: $it")
                    addUserToDatabase(name,email,auth.currentUser?.uid!!, it.toString())
                }
            }
    }
}