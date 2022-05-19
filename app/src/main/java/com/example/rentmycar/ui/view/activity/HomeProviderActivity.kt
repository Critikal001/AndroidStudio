package com.example.rentmycar.ui.view.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.rentmycar.MainActivity
import com.example.rentmycar.R
import com.example.rentmycar.databinding.ActivityHomeProviderBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.internal.NavigationMenuItemView
import com.google.android.material.navigation.NavigationView


class HomeProviderActivity : AppCompatActivity(), MyDrawerController {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeProviderBinding
    private lateinit var menuButton: FloatingActionButton
    private lateinit var  drawerLayout: DrawerLayout


    companion object {
        lateinit var context: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window: Window = this@HomeProviderActivity.window
        context = this
        binding = ActivityHomeProviderBinding.inflate(layoutInflater)

        setContentView(binding.root)

        menuButton = binding.appBarHome.menuButton
        drawerLayout = binding.drawerLayout

        menuButton.setOnClickListener {
            if(!drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.openDrawer(GravityCompat.START);
            else drawerLayout.closeDrawer(GravityCompat.END);

            val navView: NavigationView = binding.navView
            val navController = findNavController(R.id.nav_host_fragment_content_provider)

            appBarConfiguration = AppBarConfiguration(
                setOf(
                R.id.createRentalFragment, R.id.rentalOverview, R.id.providerDashboardFragment, R.id.logout
                )
            )
            navView.setupWithNavController(navController)

            val button : NavigationMenuItemView = findViewById(R.id.logout)
            button.setOnClickListener {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_provider)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    

    override fun setDrawer_Locked() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        menuButton.visibility = View.GONE
    }

    override fun setDrawer_UnLocked() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        menuButton.visibility = View.VISIBLE

    }
}