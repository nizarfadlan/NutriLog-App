package com.nutrilog.app.presentation.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nutrilog.app.databinding.FragmentSignInBinding
import com.nutrilog.app.presentation.ui.base.BaseFragment
import com.nutrilog.app.presentation.ui.main.MainActivity

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
            btnSignin.setOnClickListener {
                moveToMain()
            }
        }
    }

    private fun moveToMain() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun moveToSignUp() {
        findNavController().navigate(
            SignInFragmentDirections.actionSignInFragmentToSignUpFragment(),
        )
    }
}
