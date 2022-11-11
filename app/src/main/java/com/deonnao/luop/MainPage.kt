package com.deonnao.luop


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
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
        replaceFragment(homeFragments)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        // Set default selection
        bottomNavigationView.selectedItemId = R.id.homeFragment

        bottomNavigationView.setOnItemSelectedListener { item ->
            var fragment: Fragment? = null
            when(item.itemId) {
                R.id.action_home -> fragment = homeFragments
                R.id.action_news -> fragment = newsFragments
                R.id.action_message -> fragment = messageFragments
                R.id.action_profile -> fragment = profileFragments
            }

            if(fragment != null) {
                replaceFragment(fragment)
            }
            true
        }
            //Log.i("MainPage", fragment.toString())


    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}


