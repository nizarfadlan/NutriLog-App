package com.nutrilog.app.presentation.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nutrilog.app.domain.common.ResultState
import com.nutrilog.app.domain.model.Gender
import com.nutrilog.app.domain.model.User
import com.nutrilog.app.domain.repository.AuthRepository
import com.nutrilog.app.presentation.common.OperationLiveData
import com.nutrilog.app.utils.helpers.getAgeFromBirthDate
import kotlinx.coroutines.launch
import java.util.Date

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {
    fun signIn(
        email: String,
        password: String,
    ): LiveData<ResultState<String>> {
        return OperationLiveData<ResultState<String>> {
            viewModelScope.launch {
                repository.signIn(email, password)
                    .collect {
                        postValue(it)
                    }
            }
        }
    }

    fun signUp(
        name: String,
        email: String,
        password: String,
        gender: Gender,
        dateOfBirth: Date,
    ): LiveData<ResultState<String>> {
        val age = dateOfBirth.getAgeFromBirthDate()

        // TODO: Implement dateOfBirth After CC fix API
        return OperationLiveData<ResultState<String>> {
            viewModelScope.launch {
                repository.signUp(name, email, password, gender, age)
                    .collect {
                        postValue(it)
                    }
            }
        }
    }

    fun getSession(): LiveData<User> {
        return repository.getSession().asLiveData()
    }

    val signOut: LiveData<ResultState<String>> =
        OperationLiveData<ResultState<String>> {
            viewModelScope.launch {
                repository.signOut()
                    .collect {
                        postValue(it)
                    }
            }
        }
}
