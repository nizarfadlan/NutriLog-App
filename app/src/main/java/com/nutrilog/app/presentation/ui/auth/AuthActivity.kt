package com.nutrilog.app.presentation.ui.auth

import android.view.LayoutInflater
import com.nutrilog.app.databinding.ActivityAuthBinding
import com.nutrilog.app.presentation.ui.base.BaseActivity

class AuthActivity : BaseActivity<ActivityAuthBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityAuthBinding =
        ActivityAuthBinding::inflate
}