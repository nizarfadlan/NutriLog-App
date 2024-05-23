package com.nutrilog.app.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.fragment.NavHostFragment
import com.nutrilog.app.R
import com.nutrilog.app.databinding.ActivityMainBinding
import com.nutrilog.app.presentation.ui.base.BaseActivity
import com.nutrilog.app.presentation.ui.welcome.WelcomeActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
        super.onViewBindingCreated(savedInstanceState)

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.apply {
            bottomNavigation.setOnItemSelectedListener { item ->
                println(item)
            }
            navController.addOnDestinationChangedListener { _, destination, _ ->
                bottomNavigation.setItemSelected(destination.id)
            }
        }
    }

    private fun moveToWelcome() {
        startActivity(Intent(this, WelcomeActivity::class.java))
        finish()
    }
}
