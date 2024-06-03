package com.nutrilog.app.di.module

import com.nutrilog.app.data.pref.SettingPreference
import com.nutrilog.app.data.pref.UserSessionPreference
import org.koin.dsl.module

val preferenceModule =
    module {
        single { UserSessionPreference(get()) }
        single { SettingPreference(get()) }
    }
