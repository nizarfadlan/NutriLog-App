package com.nutrilog.app.presentation.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nutrilog.app.databinding.FragmentSignInBinding
import com.nutrilog.app.presentation.ui.base.BaseFragment

class SignInFragment : BaseFragment<FragmentSignInBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignInBinding =
        FragmentSignInBinding::inflate

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            tvSignup.setOnClickListener {
                moveToSignUp()
            }
        }
    }

    private fun moveToSignUp() {
        findNavController().navigate(
            SignInFragmentDirections.actionSignInFragmentToSignUpFragment(),
        )
    }
}
