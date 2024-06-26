package com.nutrilog.app.presentation.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nutrilog.app.databinding.FragmentHomeBinding
import com.nutrilog.app.presentation.ui.auth.AuthViewModel
import com.nutrilog.app.presentation.ui.base.BaseFragment
import com.nutrilog.app.presentation.ui.main.home.adapter.HomeAdapter
import com.nutrilog.app.presentation.ui.main.profile.ProfileViewModel
import com.nutrilog.app.utils.helpers.convertStringToDate
import com.nutrilog.app.utils.helpers.getAgeFromBirthDate
import com.nutrilog.app.utils.helpers.observe
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Date

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val authViewModel: AuthViewModel by viewModel()
    private val profileViewModel: ProfileViewModel by viewModel()
    private val homeViewModel: HomeViewModel by viewModel()
    private val homeAdapter by lazy { HomeAdapter() }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        authViewModel.getSession().observe(viewLifecycleOwner) {
            binding.tvName.text = it.name
        }

        initRecyclerView()
        initObserve()
        initAction()
    }

    private fun initRecyclerView() {
        binding.rvNutrients.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeAdapter
        }
    }

    private fun initObserve() {
        val date = Date()
        observe(homeViewModel.calculateNutrients(date)) {
            homeAdapter.setNutritionData(it)
        }

        observe(authViewModel.getSession()) { user ->
            user.dateOfBirth.takeIf { it != "" }?.let {
                val dateStringToDate = user.dateOfBirth.convertStringToDate()
                dateStringToDate.takeIf { it != null }?.let { dateOfBirth ->
                    homeAdapter.setUserData(
                        dateOfBirth.getAgeFromBirthDate(),
                        user.gender.label,
                    )
                }
            }
        }

        observe(profileViewModel.getActiveLevel()) {
            homeAdapter.setActiveLevel(
                it,
            )
        }

        observe(profileViewModel.getUserWeight()) {
            homeAdapter.setWeight(
                it,
            )
        }

        observe(profileViewModel.getUserHeight()) {
            homeAdapter.setHeight(
                it,
            )
        }
    }

    private fun initAction() {
        binding.aboutUsButton.setOnClickListener {
            moveToAbout()
        }
    }

    private fun moveToAbout() {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToAboutActivity(),
        )
    }
}
