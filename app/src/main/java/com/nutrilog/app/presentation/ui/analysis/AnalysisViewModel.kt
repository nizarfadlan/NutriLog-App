package com.nutrilog.app.presentation.ui.analysis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutrilog.app.domain.common.ResultState
import com.nutrilog.app.domain.model.NutritionFood
import com.nutrilog.app.domain.repository.NutritionRepository
import com.nutrilog.app.presentation.common.OperationLiveData
import kotlinx.coroutines.launch

class AnalysisViewModel(private val repository: NutritionRepository) : ViewModel() {
    fun saveNutrition(
        foodName: String,
        carbohydrate: Float,
        proteins: Float,
        fat: Float,
        calories: Float,
    ) = OperationLiveData<ResultState<String>> {
        viewModelScope.launch {
            repository.saveNutrition(
                foodName,
                carbohydrate,
                proteins,
                fat,
                calories,
            ).collect { postValue(it) }
        }
    }

    fun fetchNutritionFood(foodName: String) =
        OperationLiveData<ResultState<NutritionFood>> {
            viewModelScope.launch {
                repository.fetchNutritionFood(foodName).collect { postValue(it) }
            }
        }
}
