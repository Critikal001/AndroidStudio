package com.example.rentmycar

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.rentmycar.ui.adapter.ViewPagerAdapter
import com.example.rentmycar.ui.view.fragment.LoginFragment
import com.example.rentmycar.ui.view.fragment.SignUpFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Creating the login page and the registerpage
//
//        // Create the object of Toolbar, ViewPager and
//        // TabLayout and use “findViewById()” method*/
//        var tab_toolbar = findViewById<Toolbar>(R.id.toolbar)
//        var tab_viewpager = findViewById<ViewPager>(R.id.tab_viewpager)
//        var tab_tablayout = findViewById<TabLayout>(R.id.tab_tablayout)
//
//        // As we set NoActionBar as theme to this activity
//        // so when we run this project then this activity doesn't
//        // show title. And for this reason, we need to run
//        // setSupportActionBar method
////        setSupportActionBar(tab_toolbar)
//        setupViewPager(tab_viewpager)
//
//        // If we dont use setupWithViewPager() method then
//        // tabs are not used or shown when activity opened
//        tab_tablayout.setupWithViewPager(tab_viewpager)
//    }
//
//    // This function is used to add items in arraylist and assign
//    // the adapter to view pager
//    private fun setupViewPager(viewpager: ViewPager) {
//        var adapter: ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
//
//        // LoginFragment is the name of Fragment and the Login
//        // is a title of tab
//        adapter.addFragment(LoginFragment(), "Login")
//        adapter.addFragment(SignUpFragment(), "Signup")
//
//        // setting adapter to view pager.
//        viewpager.setAdapter(adapter)
//    }

    }
}