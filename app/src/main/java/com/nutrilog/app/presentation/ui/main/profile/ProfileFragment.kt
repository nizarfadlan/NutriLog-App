package com.nutrilog.app.presentation.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.nutrilog.app.R
import com.nutrilog.app.databinding.FragmentProfileBinding
import com.nutrilog.app.domain.common.ResultState
import com.nutrilog.app.domain.model.Language
import com.nutrilog.app.presentation.ui.auth.AuthViewModel
import com.nutrilog.app.presentation.ui.base.BaseFragment
import com.nutrilog.app.utils.helpers.gone
import com.nutrilog.app.utils.helpers.observe
import com.nutrilog.app.utils.helpers.show
import com.nutrilog.app.utils.helpers.showSnackBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    private val authViewModel: AuthViewModel by viewModel()
    private val profileViewModel: ProfileViewModel by viewModel()

    private var currentLanguage: Language = Language.ENGLISH

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding =
        FragmentProfileBinding::inflate

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.getSession().observe(viewLifecycleOwner) {
            binding.tvUsername.text = it.name
            binding.tvEmail.text = it.email
        }

        initObserver()
        initUI()
    }

    private fun initObserver() {
        observe(profileViewModel.getLanguage(), ::handleLanguageChange)
    }

    private fun initUI() {
        binding.apply {
            tvTitleProfile.text = getString(R.string.header_about_title)

            switchTranslate.setOnCheckedChangeListener { _, isChecked ->
                applyLanguage(isChecked)
            }

            logoutSection.setOnClickListener {
                observe(authViewModel.signOut, ::onSignOutResult)
            }
        }
    }

    private fun handleLanguageChange(language: Language) {
        currentLanguage = language
        setSwitchLanguage(language)
    }

    private fun setSwitchLanguage(language: Language) {
        binding.switchTranslate.apply {
            isChecked = language == Language.ENGLISH
            thumbDrawable =
                AppCompatResources.getDrawable(
                    context,
                    if (language == Language.ENGLISH) R.drawable.flag_gb else R.drawable.flag_id,
                )
        }
    }

    private fun applyLanguage(isEnglish: Boolean) {
        if (isEnglish) {
            profileViewModel.saveLanguageSetting(Language.ENGLISH)
        } else {
            profileViewModel.saveLanguageSetting(Language.INDONESIA)
        }
    }

    private fun onSignOutResult(result: ResultState<String>) {
        when (result) {
            is ResultState.Loading -> showLoading(true)
            is ResultState.Success -> showLoading(false)
            is ResultState.Error -> {
                showLoading(false)
                binding.root.showSnackBar(result.message)
            }
        }
    }

    private fun showLoading(status: Boolean) {
        binding.loadingLayout.root.apply {
            if (status) show() else gone()
        }
    }
}
