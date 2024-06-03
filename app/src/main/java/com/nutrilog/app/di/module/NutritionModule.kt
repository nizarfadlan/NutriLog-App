package com.nutrilog.app.di.module

import com.nutrilog.app.data.datasource.NutritionDataSourceImpl
import com.nutrilog.app.data.repository.NutritionRepositoryImpl
import com.nutrilog.app.domain.datasource.NutritionDataSource
import com.nutrilog.app.domain.repository.NutritionRepository
import org.koin.dsl.module

val nutritionDataSourceModule =
    module {
        single<NutritionDataSource> { NutritionDataSourceImpl(get(), get()) }
    }

val nutritionRepositoryModule =
    module {
        factory<NutritionRepository> { NutritionRepositoryImpl(get()) }
    }
