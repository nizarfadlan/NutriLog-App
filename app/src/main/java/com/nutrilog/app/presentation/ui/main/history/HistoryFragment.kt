package com.nutrilog.app.presentation.ui.main.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.nutrilog.app.databinding.FragmentHistoryBinding
import com.nutrilog.app.presentation.ui.base.BaseFragment
import com.nutrilog.app.presentation.ui.base.component.CenterSmoothScroller
import com.nutrilog.app.presentation.ui.main.history.adapter.DatesAdapter
import com.nutrilog.app.utils.helpers.DayInfo
import com.nutrilog.app.utils.helpers.getCalender
import com.nutrilog.app.utils.helpers.getDaysInMonth
import com.nutrilog.app.utils.helpers.getTimeInMillis
import com.nutrilog.app.utils.helpers.observe
import com.nutrilog.app.utils.helpers.setMonth
import java.util.Date

class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {
    private lateinit var dates: List<DayInfo>
    private var dateMilliseconds: Long = MaterialDatePicker.todayInUtcMilliseconds()
    private val historyViewModel by viewModels<HistoryViewModel>()

    private val listDatesAdapter by lazy {
        DatesAdapter { date -> updateActiveDate(date.dayOfMonth) }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHistoryBinding =
        FragmentHistoryBinding::inflate

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initData()
        initObserve()
        initActions()
    }

    private fun initRecyclerView() {
        binding.apply {
            rvDates.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = listDatesAdapter
                isNestedScrollingEnabled = false
            }
        }
    }

    private fun initActions() {
        binding.apply {
            btnCalender.setOnClickListener {
                showDialogCalender()
            }

            selectMonth.setOnSpinnerItemSelectedListener<String> { oldIndex, _, newIndex, _ ->
                if (oldIndex != newIndex) {
                    historyViewModel.setMonth(newIndex)
                }
            }
        }
    }

    private fun initData() {
        setDate()
    }

    private fun initObserve() {
        observe(historyViewModel.currentFullDate) {
            val (date, month, year) = it
            println("date: $date month: $month year: $year")

            getDays(month, year)
            binding.selectMonth.setMonth(month)
            listDatesAdapter.updateActiveDay(date)
            dateMilliseconds = getTimeInMillis(date, month, year)
            smoothScrollToActiveItem(date - 1)
        }
    }

    private fun smoothScrollToActiveItem(position: Int) {
        binding.rvDates.post {
            val layoutManager = binding.rvDates.layoutManager as? LinearLayoutManager
            layoutManager?.let {
                val smoothScroller =
                    CenterSmoothScroller(requireContext()).apply {
                        targetPosition = position
                    }
                it.startSmoothScroll(smoothScroller)
            }
        }
    }

    private fun showDialogCalender() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(dateMilliseconds)
                .build()

        datePicker.addOnPositiveButtonClickListener {
            setDate(it)
        }

        datePicker.show(parentFragmentManager, "datePicker")
    }

    private fun updateActiveDate(newDate: Int) {
        historyViewModel.setDate(newDate)
    }

    private fun setDate(date: Long? = null) {
        val dateObject = date?.let { Date(it) } ?: Date()
        val (day, month, year) = dateObject.getCalender(locale)
        historyViewModel.setFullDate(day, month, year)
    }

    private fun getDays(
        month: Int,
        year: Int,
    ) {
        dates = getDaysInMonth(month, year)
        listDatesAdapter.listDates = dates
    }
}
