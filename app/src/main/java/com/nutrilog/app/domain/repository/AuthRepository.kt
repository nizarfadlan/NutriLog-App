package com.nutrilog.app.domain.repository

import com.nutrilog.app.domain.common.ResultState
import com.nutrilog.app.domain.model.Gender
import com.nutrilog.app.domain.model.User
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface AuthRepository {
    suspend fun signUp(
        name: String,
        email: String,
        password: String,
        gender: Gender,
        dateOfBirth: Date,
    ): Flow<ResultState<String>>

    suspend fun signIn(
        email: String,
        password: String,
    ): Flow<ResultState<String>>

    fun getSession(): Flow<User>

    suspend fun signOut(): Flow<ResultState<String>>
}
