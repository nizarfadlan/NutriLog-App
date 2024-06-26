package com.nutrilog.app.data.repository

import com.nutrilog.app.data.remote.request.LoginRequest
import com.nutrilog.app.data.remote.request.RegisterRequest
import com.nutrilog.app.domain.common.ResultState
import com.nutrilog.app.domain.datasource.AuthDataSource
import com.nutrilog.app.domain.model.Gender
import com.nutrilog.app.domain.model.User
import com.nutrilog.app.domain.repository.AuthRepository
import com.nutrilog.app.utils.helpers.convertDateToString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import java.util.Date

class AuthRepositoryImpl(
    private val dataSource: AuthDataSource,
) : AuthRepository {
    override suspend fun signUp(
        name: String,
        email: String,
        password: String,
        gender: Gender,
        dateOfBirth: Date,
    ): Flow<ResultState<String>> {
        val convertDateToString = dateOfBirth.convertDateToString()
        val requestData = RegisterRequest(name, email, password, gender.label, convertDateToString)
        return dataSource.signUp(requestData).flowOn(Dispatchers.IO)
    }

    override suspend fun signIn(
        email: String,
        password: String,
    ): Flow<ResultState<String>> {
        val requestData = LoginRequest(email, password)
        return dataSource.signIn(requestData).flowOn(Dispatchers.IO)
    }

    override fun getSession(): Flow<User> = dataSource.getSession().flowOn(Dispatchers.IO)

    override suspend fun signOut(): Flow<ResultState<String>> {
        return dataSource.signOut().flowOn(Dispatchers.IO)
    }
}
