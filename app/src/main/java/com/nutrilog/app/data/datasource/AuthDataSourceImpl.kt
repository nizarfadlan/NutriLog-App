package com.nutrilog.app.data.datasource

import com.nutrilog.app.data.pref.UserSessionPreference
import com.nutrilog.app.data.remote.request.LoginRequest
import com.nutrilog.app.data.remote.request.RegisterRequest
import com.nutrilog.app.data.remote.service.AuthService
import com.nutrilog.app.di.module.preferenceModule
import com.nutrilog.app.di.module.remoteModule
import com.nutrilog.app.domain.common.ResultState
import com.nutrilog.app.domain.datasource.AuthDataSource
import com.nutrilog.app.domain.model.User
import kotlinx.coroutines.flow.Flow
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class AuthDataSourceImpl(
    private val service: AuthService,
    private val pref: UserSessionPreference,
) : AuthDataSource {
    override suspend fun signUp(request: RegisterRequest): Flow<ResultState<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun signIn(request: LoginRequest): Flow<ResultState<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun signOut(): Flow<ResultState<String>> {
        TODO("Not yet implemented")
    }

    override fun getSession(): Flow<User> {
        TODO("Not yet implemented")
    }

    private fun reloadModule() {
        unloadKoinModules(preferenceModule)
        loadKoinModules(preferenceModule)

        unloadKoinModules(remoteModule)
        loadKoinModules(remoteModule)
    }
}
