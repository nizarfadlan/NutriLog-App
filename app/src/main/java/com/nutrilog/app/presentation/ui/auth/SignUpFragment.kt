package com.nutrilog.app.presentation.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nutrilog.app.R
import com.nutrilog.app.databinding.FragmentSignUpBinding
import com.nutrilog.app.domain.common.ResultState
import com.nutrilog.app.domain.model.Gender
import com.nutrilog.app.presentation.ui.base.BaseFragment
import com.nutrilog.app.utils.constant.AppConstant.PASSWORD_LENGTH
import com.nutrilog.app.utils.helpers.gone
import com.nutrilog.app.utils.helpers.isLengthPasswordCorrect
import com.nutrilog.app.utils.helpers.observe
import com.nutrilog.app.utils.helpers.show
import com.nutrilog.app.utils.helpers.showSnackBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {
    private val authViewModel: AuthViewModel by viewModel()

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

            btnSignup.setOnClickListener {
                signUpRequest()
            }
        }
    }

    private fun signUpRequest() {
        val name = binding.edRegisterName.text.toString()
        val email = binding.edRegisterEmail.text.toString()
        val password = binding.edRegisterPassword.text.toString()
        when {
            name.isEmpty() ->
                binding.edRegisterName.error =
                    getString(R.string.validation_must_not_empty)

            email.isEmpty() ->
                binding.edRegisterEmail.error =
                    getString(R.string.validation_must_not_empty)

            password.isEmpty() ->
                binding.edRegisterPassword.error =
                    getString(R.string.validation_must_not_empty)

            !password.isLengthPasswordCorrect ->
                binding.edRegisterPassword.error =
                    String.format(
                        getString(
                            R.string.validation_password,
                        ),
                        PASSWORD_LENGTH,
                    )

            else -> {
                // TODO: Change gender and age
                signUpProcess(name, email, password, Gender.OTHER, 0)
            }
        }
    }

    private fun signUpProcess(
        name: String,
        email: String,
        password: String,
        gender: Gender,
        age: Int,
    ) {
        observe(authViewModel.signUp(name, email, password, gender, age), ::showResultSignUp)
    }

    private fun showResultSignUp(result: ResultState<String>) {
        when (result) {
            is ResultState.Loading -> showLoading(true)

            is ResultState.Success -> {
                showLoading(false)
                binding.root.showSnackBar(result.data)
                backSignIn()
            }

            is ResultState.Error -> {
                showLoading(false)
                binding.root.showSnackBar(result.message)
            }
        }
    }

    private fun backSignIn() {
        findNavController().popBackStack()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.loadingLayout.root.apply {
            if (isLoading) show() else gone()
        }
    }
}
