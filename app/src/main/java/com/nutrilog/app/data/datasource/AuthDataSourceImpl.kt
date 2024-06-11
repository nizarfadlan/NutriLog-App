package com.nutrilog.app.data.datasource

import com.nutrilog.app.data.pref.UserSessionPreference
import com.nutrilog.app.data.remote.request.LoginRequest
import com.nutrilog.app.data.remote.request.RegisterRequest
import com.nutrilog.app.data.remote.service.AuthService
import com.nutrilog.app.di.module.preferenceModule
import com.nutrilog.app.di.module.remoteModule
import com.nutrilog.app.domain.common.ResultState
import com.nutrilog.app.domain.common.StatusResponse
import com.nutrilog.app.domain.datasource.AuthDataSource
import com.nutrilog.app.domain.model.User
import com.nutrilog.app.utils.helpers.createResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class AuthDataSourceImpl(
    private val service: AuthService,
    private val pref: UserSessionPreference,
) : AuthDataSource {
    override suspend fun signUp(request: RegisterRequest): Flow<ResultState<String>> =
        flow {
            emit(ResultState.Loading)
            try {
                val response = service.register(request)
                if (response.status === StatusResponse.ERROR) {
                    emit(ResultState.Error(response.message))
                    return@flow
                }

                emit(ResultState.Success(response.message))
            } catch (e: Exception) {
                emit(ResultState.Error(e.createResponse()?.message ?: ""))
            }
        }

    override suspend fun signIn(request: LoginRequest): Flow<ResultState<String>> =
        flow {
            emit(ResultState.Loading)
            try {
                val response = service.login(request)
                println(response.data)
                if (response.status === StatusResponse.ERROR) {
                    emit(ResultState.Error(response.message))
                    return@flow
                }

                pref.saveSession(response.data)
                reloadModule()
                emit(ResultState.Success(response.message))
            } catch (e: Exception) {
                emit(ResultState.Error(e.createResponse()?.message ?: ""))
            }
        }

    override suspend fun signOut(): Flow<ResultState<String>> =
        flow {
            emit(ResultState.Loading)
            try {
                pref.logout()
                reloadModule()
                emit(ResultState.Success("Logout success"))
            } catch (e: Exception) {
                emit(ResultState.Error(e.createResponse()?.message ?: ""))
            }
        }

    override fun getSession(): Flow<User> = pref.getSession()

    private fun reloadModule() {
        unloadKoinModules(preferenceModule)
        loadKoinModules(preferenceModule)

        unloadKoinModules(remoteModule)
        loadKoinModules(remoteModule)
    }
}
