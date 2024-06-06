package com.nutrilog.app.presentation.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutrilog.app.domain.model.NutritionOption
import com.nutrilog.app.domain.repository.NutritionRepository
import com.nutrilog.app.presentation.common.OperationLiveData
import kotlinx.coroutines.launch
import java.util.Date

class HomeViewModel(private val repository: NutritionRepository) : ViewModel() {
    fun calculateNutrients(date: Date) =
        OperationLiveData<Map<NutritionOption, Double>> {
            viewModelScope.launch {
                repository.calculateNutritionByDate(date).collect { postValue(it) }
            }
        }
}
