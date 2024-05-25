package com.nutrilog.app.presentation.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nutrilog.app.databinding.FragmentHomeBinding
import com.nutrilog.app.presentation.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate
}
