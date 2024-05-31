package com.nutrilog.app.di

import com.nutrilog.app.di.module.localModule
import com.nutrilog.app.di.module.preferenceModule
import com.nutrilog.app.di.module.remoteModule

val appModule =
    listOf(
        localModule,
        preferenceModule,
        remoteModule,
    )
