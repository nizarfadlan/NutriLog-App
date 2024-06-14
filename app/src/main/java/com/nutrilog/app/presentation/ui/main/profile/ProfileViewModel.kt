package com.nutrilog.app.presentation.ui.main.profile

import androidx.camera.core.processing.SurfaceProcessorNode.In
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nutrilog.app.data.pref.SettingPreference
import com.nutrilog.app.domain.model.ActiveLevel
import com.nutrilog.app.domain.model.Language
import kotlinx.coroutines.launch

class ProfileViewModel(private val preference: SettingPreference): ViewModel() {
    fun getLanguage():LiveData<Language> = preference.getLanguageSetting().asLiveData()

    fun getActiveLevel():LiveData<ActiveLevel> = preference.getActiveLevelSetting().asLiveData()

    fun getUserWeight():LiveData<Int> = preference.getWeightUser().asLiveData()

    fun getUserHeight():LiveData<Int> = preference.getHeightUser().asLiveData()

    fun saveLanguageSetting(language: Language) {
        viewModelScope.launch {
            preference.saveLanguageSetting(language)
        }
    }

    fun saveActiveLevelSetting(activeLevel: ActiveLevel) {
        viewModelScope.launch {
            preference.setActiveLevelSetting(activeLevel)
        }
    }

    fun saveUserWeight(weight: Int) {
        viewModelScope.launch {
            preference.setWeightUser(weight)
        }
    }

    fun saveUserHeight(height: Int) {
        viewModelScope.launch {
            preference.setHeightUser(height)
        }
    }
}