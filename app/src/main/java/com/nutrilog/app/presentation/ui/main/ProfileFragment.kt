package com.nutrilog.app.presentation.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nutrilog.app.databinding.FragmentProfileBinding
import com.nutrilog.app.presentation.ui.base.BaseFragment

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding =
        FragmentProfileBinding::inflate
}
