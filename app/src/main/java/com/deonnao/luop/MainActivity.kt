package com.deonnao.luop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

const val TAG = "MainActivity"
private lateinit var auth: FirebaseAuth
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize Firebase Auth
        auth = Firebase.auth

        val etStudentId = findViewById<EditText>(R.id.etStudentId)
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val loginBtn = findViewById<Button>(R.id.loginBtn)
        val signUpBtn = findViewById<Button>(R.id.signUpBtn)

        val username = etUsername.text.toString()
        val password = etPassword.text.toString()
        Log.i(TAG, username)
        loginBtn.setOnClickListener {
            val intent = Intent(this, MainPage::class.java)
            Toast.makeText(this, "Successfully logged in!", Toast.LENGTH_SHORT).show()
            startActivity(intent)
            /*auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        Toast.makeText(this, "Successfully logged in!", Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }*/

        }
        signUpBtn.setOnClickListener {
            Toast.makeText(this, "Successfully signed up!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainPage::class.java)
            startActivity(intent)
           /* auth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        Toast.makeText(this, "Successfully signed up!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainPage::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }*/
        }

    }
}