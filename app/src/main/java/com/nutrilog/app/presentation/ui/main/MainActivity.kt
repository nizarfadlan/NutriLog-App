package com.nutrilog.app.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import com.nutrilog.app.databinding.ActivityMainBinding
import com.nutrilog.app.presentation.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
        super.onViewBindingCreated(savedInstanceState)

    }
}