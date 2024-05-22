package com.nutrilog.app.presentation.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nutrilog.app.databinding.FragmentSignUpBinding
import com.nutrilog.app.presentation.ui.base.BaseFragment

class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignUpBinding =
        FragmentSignUpBinding::inflate

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            tvSignin.setOnClickListener {
                backSignIn()
            }
        }
    }

    private fun backSignIn() {
        findNavController().popBackStack()
    }
}
