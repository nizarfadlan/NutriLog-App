package com.nutrilog.app.di

import com.nutrilog.app.di.module.authDataSourceModule
import com.nutrilog.app.di.module.authRepositoryModule
import com.nutrilog.app.di.module.localModule
import com.nutrilog.app.di.module.nutritionDataSourceModule
import com.nutrilog.app.di.module.nutritionRepositoryModule
import com.nutrilog.app.di.module.preferenceModule
import com.nutrilog.app.di.module.remoteModule
import com.nutrilog.app.di.module.viewModelModule

val appModule =
    listOf(
        localModule,
        preferenceModule,
        remoteModule,
        viewModelModule,
        authDataSourceModule,
        authRepositoryModule,
        nutritionDataSourceModule,
        nutritionRepositoryModule,
    )
