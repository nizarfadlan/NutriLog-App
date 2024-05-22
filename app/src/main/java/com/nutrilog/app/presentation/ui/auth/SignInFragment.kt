package com.nutrilog.app.presentation.ui.auth

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nutrilog.app.databinding.FragmentSignInBinding
import com.nutrilog.app.presentation.ui.base.BaseFragment

class SignInFragment : BaseFragment<FragmentSignInBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignInBinding =
        FragmentSignInBinding::inflate


}