package com.nutrilog.app.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.fragment.NavHostFragment
import com.nutrilog.app.R
import com.nutrilog.app.databinding.ActivityMainBinding
import com.nutrilog.app.presentation.ui.auth.AuthViewModel
import com.nutrilog.app.presentation.ui.base.BaseActivity
import com.nutrilog.app.presentation.ui.welcome.WelcomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val authViewModel: AuthViewModel by viewModel()

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
                when (item) {
                    R.id.homeFragment -> navController.navigate(R.id.homeFragment)
                    R.id.historyFragment -> navController.navigate(R.id.historyFragment)
                    R.id.profileFragment -> navController.navigate(R.id.profileFragment)
                }
            }
            navController.addOnDestinationChangedListener { _, destination, _ ->
                bottomNavigation.setItemSelected(destination.id)
            }
        }
    }

    override fun onStart() {
        super.onStart()

        authViewModel.getSession().observe(this) {
            if (it.id === "" || it === null) {
                moveToWelcome()
            }
        }
    }

    private fun moveToWelcome() {
        startActivity(Intent(this, WelcomeActivity::class.java))
        finish()
    }
}
