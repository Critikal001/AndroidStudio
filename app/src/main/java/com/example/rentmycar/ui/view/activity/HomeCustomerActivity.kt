package com.example.rentmycar.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.rentmycar.MainActivity
import com.example.rentmycar.R
import com.example.rentmycar.databinding.ActivityHomeCustomerBinding
import com.example.rentmycar.databinding.ActivityHomeProviderBinding
import com.example.rentmycar.utils.Utils
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
class HomeCustomerActivity : AppCompatActivity(), MyDrawerController {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeCustomerBinding
    private lateinit var menuButton: FloatingActionButton
    private lateinit var  drawerLayout: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window: Window = this@HomeCustomerActivity.window

        binding = ActivityHomeCustomerBinding.inflate(layoutInflater)

        setContentView(binding.root)

        menuButton = binding.appBarHome.menuCustomerButton
        drawerLayout = binding.customerDrawerLayout

        menuButton.setOnClickListener {
            if(!drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.openDrawer(
                GravityCompat.START);
            else drawerLayout.closeDrawer(GravityCompat.END);

            val navView: NavigationView = binding.customerNavView
            val navController = findNavController(R.id.nav_host_fragment_content_provider)

            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.rental_list, R.id.reservation_overview, R.id.rental_location, R.id.dashboard_customer, R.id.logout
                )
            )
            navView.setupWithNavController(navController)
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