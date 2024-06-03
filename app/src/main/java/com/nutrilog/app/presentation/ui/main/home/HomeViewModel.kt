package com.nutrilog.app.presentation.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutrilog.app.domain.common.ResultState
import com.nutrilog.app.domain.model.Nutrition
import com.nutrilog.app.domain.repository.NutritionRepository
import com.nutrilog.app.presentation.common.OperationLiveData
import kotlinx.coroutines.launch
import java.util.Date

class HomeViewModel(private val repository: NutritionRepository) : ViewModel() {
    fun fetchNutrients(date: Date) =
        OperationLiveData<ResultState<List<Nutrition>>> {
            viewModelScope.launch { repository.fetchNutrients(date).collect { postValue(it) } }
        }

    fun fetchNutrition(id: String) =
        OperationLiveData<ResultState<Nutrition>> {
            viewModelScope.launch { repository.fetchNutrition(id).collect { postValue(it) } }
        }
}
