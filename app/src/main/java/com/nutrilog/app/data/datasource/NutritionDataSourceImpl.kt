package com.nutrilog.app.data.datasource

import com.nutrilog.app.data.local.room.NutrilogDatabase
import com.nutrilog.app.data.remote.service.NutritionService
import com.nutrilog.app.domain.common.ResultState
import com.nutrilog.app.domain.datasource.NutritionDataSource
import com.nutrilog.app.domain.model.Nutrition
import kotlinx.coroutines.flow.Flow
import java.util.Date

class NutritionDataSourceImpl(
    private val database: NutrilogDatabase,
    private val service: NutritionService,
) : NutritionDataSource {
    override suspend fun fetchNutrients(date: Date?): Flow<ResultState<List<Nutrition>>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchNutrition(id: String): Flow<ResultState<Nutrition>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNutrition(nutrition: Nutrition): Flow<ResultState<String>> {
        TODO("Not yet implemented")
    }
}
