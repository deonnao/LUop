package com.deonnao.luop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import androidx.fragment.app.Fragment
import com.deonnao.luop.fragments.HomeFragment
import com.deonnao.luop.fragments.MessageFragment
import com.deonnao.luop.fragments.NewsFragment
import com.deonnao.luop.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        // define your fragments here
        val homeFragments: Fragment = HomeFragment()
        val newsFragments: Fragment = NewsFragment()
        val messageFragments: Fragment = MessageFragment()
        val profileFragments: Fragment = ProfileFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            var fragment: Fragment? = null
            when (item.itemId) {
                R.id.homeFragment -> fragment = homeFragments
                R.id.newsFragment -> fragment = newsFragments
                R.id.messageFragment -> fragment = messageFragments
                R.id.profileFragment -> fragment = profileFragments
            }
            if(fragment != null) {
                replaceFragment(fragment)
            }
            true
        }
        // Set default selection
        bottomNavigationView.selectedItemId = R.id.homeFragment

    }

    private fun replaceFragment(homeFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, homeFragment)
        fragmentTransaction.commit()
    }
}


