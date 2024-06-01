package com.nutrilog.app.di.module

import com.nutrilog.app.presentation.ui.auth.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule =
    module {
        viewModel { AuthViewModel(get()) }
    }
