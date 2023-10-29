package com.example.womensafetyapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.womensafetyapplication.databinding.ActivityMainBinding
import com.firebase.ui.auth.AuthUI


class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private fun getNavController(): NavController {
        val fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.myNavHostFragment)
        check(fragment is NavHostFragment) {
            ("Activity " + this
                    + " does not have a NavHostFragment")
        }
        return (fragment as NavHostFragment?)!!.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        drawerLayout = binding.drawerLayout

        val navController = getNavController()
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)

        NavigationUI.setupWithNavController(binding.navView, navController)

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.logout_user ->
                    AuthUI.getInstance().signOut(this)
                R.id.guardianInfo ->
                    navController.navigate(R.id.action_dashBoardFragment_to_guardianInfo)
                R.id.mapsActivity ->
                    navController.navigate(R.id.action_dashBoardFragment_to_mapsActivity)
                R.id.nearestPoliceStation ->
                    navController.navigate(R.id.action_dashBoardFragment_to_NearestPoliceStation)
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onSupportNavigateUp():Boolean{
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController,drawerLayout)
    }
}
