package com.nutrilog.app.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.datepicker.MaterialDatePicker
import com.nutrilog.app.databinding.FragmentHistoryBinding
import com.nutrilog.app.presentation.ui.base.BaseFragment
import com.nutrilog.app.utils.helpers.DayInfo
import com.nutrilog.app.utils.helpers.getCalender
import com.nutrilog.app.utils.helpers.getDaysInMonth
import com.nutrilog.app.utils.helpers.getTimeInMillis
import com.skydoves.powerspinner.PowerSpinnerView
import java.util.Date

class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {
    private lateinit var dates: List<DayInfo>
    private var currentDate: Int? = null
    private var currentMonth: Int? = null
    private var currentYear: Int? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHistoryBinding =
        FragmentHistoryBinding::inflate

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initActions()
    }

    private fun initActions() {
        binding.apply {
            btnCalender.setOnClickListener {
                showDialogCalender()
            }
            selectMonth.setOnSpinnerItemSelectedListener<String> { oldIndex, _, newIndex, _ ->
                if (oldIndex != newIndex) {
                    currentMonth = newIndex
                    getDays()
                }
            }
        }
    }

    private fun initData() {
        setDate()

        binding.apply {
            selectMonth.apply {
                lifecycleOwner = viewLifecycleOwner
                setMonth()
            }
        }
    }

    private fun showDialogCalender() {
        val dateMilliseconds =
            currentYear?.let { year ->
                currentMonth?.let { month ->
                    currentDate?.let { day ->
                        getTimeInMillis(day, month, year)
                    }
                }
            } ?: MaterialDatePicker.todayInUtcMilliseconds()

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

    private fun setDayMonthYear(
        day: Int,
        month: Int,
        year: Int,
    ) {
        currentDate = day
        currentMonth = month
        currentYear = year
    }

    private fun PowerSpinnerView.setMonth() {
        currentMonth?.also { this.selectItemByIndex(it) }
    }

    private fun setDate(date: Long? = null) {
        val dateObject = date?.let { Date(it) } ?: Date()
        val (day, month, year) = dateObject.getCalender(locale)
        setDayMonthYear(day, month, year)
        getDays()
        binding.selectMonth.setMonth()
    }

    private fun getDays() {
        currentMonth?.let { currentMonth ->
            currentYear?.let { currentYear ->
                dates = getDaysInMonth(currentYear, currentMonth)
            }
        }
    }
}
