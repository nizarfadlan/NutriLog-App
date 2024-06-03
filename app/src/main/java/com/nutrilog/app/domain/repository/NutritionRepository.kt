package com.nutrilog.app.domain.repository

import com.nutrilog.app.domain.common.ResultState
import com.nutrilog.app.domain.model.Nutrition
import kotlinx.coroutines.flow.Flow
import java.io.File
import java.util.Date

interface NutritionRepository {
    suspend fun fetchNutrients(date: Date): Flow<ResultState<List<Nutrition>>>

    suspend fun fetchNutrition(id: String): Flow<ResultState<Nutrition>>

    suspend fun saveNutrition(image: File): Flow<ResultState<String>>
}
