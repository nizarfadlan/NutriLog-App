package com.nutrilog.app.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.nutrilog.app.databinding.ActivityMainBinding
import com.nutrilog.app.presentation.ui.base.BaseActivity
import com.nutrilog.app.presentation.ui.welcome.WelcomeActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
        super.onViewBindingCreated(savedInstanceState)

        // Temporary code
        startActivity(Intent(this, WelcomeActivity::class.java))
        finish()
    }
}