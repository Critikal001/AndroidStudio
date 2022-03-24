package com.example.rentmycar.ui.view.activity

import android.content.Context
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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home_customer.*
import kotlinx.android.synthetic.main.app_bar_customer.view.*

class HomeCustomerActivity : AppCompatActivity(), MyDrawerController {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeCustomerBinding
    private lateinit var menuButton: FloatingActionButton
    private lateinit var  drawerLayout: DrawerLayout

    companion object {
        lateinit var context: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window: Window = this@HomeCustomerActivity.window
        context = this
        binding = ActivityHomeCustomerBinding.inflate(layoutInflater)

        setContentView(binding.root)

        menuButton = app_bar_home.menu_customer_button
        drawerLayout = binding.customerDrawerLayout

        menuButton.setOnClickListener {
            if(!drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.openDrawer(
                GravityCompat.START);
            else drawerLayout.closeDrawer(GravityCompat.END);

            val navView: NavigationView = binding.customerNavView
            val navController = findNavController(R.id.nav_host_fragment_content_customer)

            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.rentalListFragment, R.id.reservationListFragment, R.id.locationListFragment, R.id.customerDashboardFragment, R.id.logout
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