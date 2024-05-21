package com.nutrilog.app.presentation.ui.welcome

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowInsets
import android.view.WindowManager
import com.nutrilog.app.databinding.ActivityWelcomeBinding
import com.nutrilog.app.presentation.ui.base.BaseActivity

class WelcomeActivity : BaseActivity<ActivityWelcomeBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityWelcomeBinding =
        ActivityWelcomeBinding::inflate

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
        super.onViewBindingCreated(savedInstanceState)


    }

    public override fun onResume() {
        super.onResume()
        setupView()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }
}