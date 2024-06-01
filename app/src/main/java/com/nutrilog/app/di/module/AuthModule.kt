package com.nutrilog.app.di.module

import com.nutrilog.app.data.datasource.AuthDataSourceImpl
import com.nutrilog.app.data.repository.AuthRepositoryImpl
import com.nutrilog.app.domain.datasource.AuthDataSource
import com.nutrilog.app.domain.repository.AuthRepository
import org.koin.dsl.module

val authDataSourceModule =
    module {
        single<AuthDataSource> { AuthDataSourceImpl(get(), get()) }
    }

val authRepositoryImplModule =
    module {
        factory<AuthRepository> { AuthRepositoryImpl(get()) }
    }
