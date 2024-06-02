package com.nutrilog.app.presentation.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import com.nutrilog.app.R
import com.nutrilog.app.databinding.ActivityAboutBinding
import com.nutrilog.app.presentation.ui.base.BaseActivity

class AboutActivity : BaseActivity<ActivityAboutBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityAboutBinding =
        ActivityAboutBinding::inflate

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
        super.onViewBindingCreated(savedInstanceState)

        initToolbar()
    }

    private fun initToolbar() {
        binding.apply {
            toolbar.apply {
                tvPage.text = getString(R.string.header_about_title)
                backButton.setOnClickListener { finish() }
            }
        }
    }
}