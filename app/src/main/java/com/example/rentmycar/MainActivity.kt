package com.example.rentmycar

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import com.example.rentmycar.ui.view.activity.HomeCustomerActivity
import com.example.rentmycar.ui.view.activity.HomeProviderActivity
import com.example.rentmycar.ui.view.activity.LoginActivity


import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onResume() {
        super.onResume()
        login.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        customer_activity.setOnClickListener {
            val intent = Intent(this,HomeCustomerActivity::class.java)
            startActivity(intent)
            finish()
        }
        provider_activity.setOnClickListener {
            val intent = Intent(this,HomeProviderActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}