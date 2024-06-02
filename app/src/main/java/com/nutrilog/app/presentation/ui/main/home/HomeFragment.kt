package com.nutrilog.app.presentation.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nutrilog.app.databinding.FragmentHomeBinding
import com.nutrilog.app.domain.model.Nutrition
import com.nutrilog.app.presentation.ui.base.BaseFragment
import com.nutrilog.app.presentation.ui.main.home.adapter.HomeAdapter
import java.sql.Date

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    private val level = 15
    private val date = Date(123456789)

    private val nutritionList = listOf(
        Nutrition("1","1","Nasi Goreng", level.toFloat(), level.toFloat(), level.toFloat(), level.toFloat(), date)
    )

    private val homeAdapter = HomeAdapter(nutritionList)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initAction()
    }

    private fun initRecyclerView() {
        binding.rvNutrients.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeAdapter
        }
    }

    private fun initAction() {
        binding.aboutUsButton.setOnClickListener {
            moveToAbout()
        }
    }

    private fun moveToAbout() {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToAboutActivity()
        )
    }

    private fun moveToDetail(idString: String) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToProfileFragment(idString)
        )
    }
}