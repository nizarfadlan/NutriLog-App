package com.nutrilog.app.presentation.ui.auth

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nutrilog.app.databinding.FragmentSignUpBinding
import com.nutrilog.app.presentation.ui.base.BaseFragment

class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignUpBinding =
        FragmentSignUpBinding::inflate
}