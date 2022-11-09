package com.deonnao.luop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlin.math.sign

const val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize Firebase Auth
        auth = Firebase.auth


        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val loginBtn = findViewById<Button>(R.id.loginBtn)
        //val signUpBtn = findViewById<Button>(R.id.signUpBtnLoginScreen)
        val createAccountTv = findViewById<TextView>(R.id.createAccount)

        loginBtn.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            //val name = etName.text.toString()
            //Toast.makeText(this, "Successfully logged in!", Toast.LENGTH_SHORT).show()
            //startActivity(intent)
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        //val user = auth.currentUser
                        val intent = Intent(this, MainPage::class.java)
                        Toast.makeText(this, "Successfully logged in!", Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }

        }

        createAccountTv.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }

}