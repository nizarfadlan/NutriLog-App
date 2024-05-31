package com.nutrilog.app.domain.datasource

import com.nutrilog.app.data.remote.request.LoginRequest
import com.nutrilog.app.data.remote.request.RegisterRequest
import com.nutrilog.app.domain.common.ResultState
import com.nutrilog.app.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    suspend fun signUp(request: RegisterRequest): Flow<ResultState<String>>

    suspend fun signIn(request: LoginRequest): Flow<ResultState<String>>

    suspend fun signOut(): Flow<ResultState<String>>

    fun getSession(): Flow<User>
}
