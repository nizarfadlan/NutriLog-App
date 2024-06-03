package com.nutrilog.app.data.datasource

import com.nutrilog.app.data.local.room.NutrilogDatabase
import com.nutrilog.app.data.remote.service.NutritionService
import com.nutrilog.app.domain.common.ResultState
import com.nutrilog.app.domain.common.StatusResponse
import com.nutrilog.app.domain.datasource.NutritionDataSource
import com.nutrilog.app.domain.model.Nutrition
import com.nutrilog.app.utils.constant.AppConstant.MULTIPART_FILE_NAME
import com.nutrilog.app.utils.helpers.createResponse
import com.nutrilog.app.utils.helpers.toMultipart
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import java.util.Date

class NutritionDataSourceImpl(
    private val database: NutrilogDatabase,
    private val service: NutritionService,
) : NutritionDataSource {
    override suspend fun fetchNutrients(date: Date): Flow<ResultState<List<Nutrition>>> =
        flow {
            emit(ResultState.Loading)
            try {
//                val response = service.getNutrients(date.convertDateToString())
//                if (response.status === StatusResponse.ERROR) {
//                    emit(ResultState.Error(response.message))
//                    return@flow
//                }
//
//                database.getNutritionDao().insertAllNutrition(response.data)
                val data = nutritionList
                emit(ResultState.Success(data))
            } catch (e: Exception) {
                emit(ResultState.Error(e.createResponse()?.message ?: ""))
            }
        }

    override suspend fun fetchNutrition(id: String): Flow<ResultState<Nutrition>> =
        flow {
            emit(ResultState.Loading)
            try {
                val response = database.getNutritionDao().getNutrition(id)

                emit(ResultState.Success(response))
            } catch (e: Exception) {
                emit(ResultState.Error(e.createResponse()?.message ?: ""))
            }
        }

    override suspend fun saveNutrition(image: File): Flow<ResultState<String>> =
        flow {
            emit(ResultState.Loading)
            try {
                val response =
                    service.predict(
                        image = image.toMultipart(MULTIPART_FILE_NAME),
                    )

                if (response.status === StatusResponse.ERROR) {
                    emit(ResultState.Error(response.message))
                    return@flow
                }

                database.getNutritionDao().insertNutrition(response.data)
                emit(ResultState.Success(response.message))
            } catch (e: Exception) {
                emit(ResultState.Error(e.createResponse()?.message ?: ""))
            }
        }

    companion object {
        private const val level = 15
        private val date = java.sql.Date(123456789)

        private val nutritionList =
            listOf(
                Nutrition(
                    "1",
                    "1",
                    "Nasi Goreng",
                    level.toFloat(),
                    level.toFloat(),
                    level.toFloat(),
                    level.toFloat(),
                    date,
                ),
                Nutrition(
                    "2",
                    "2",
                    "Mie Goreng",
                    level.toFloat(),
                    level.toFloat(),
                    level.toFloat(),
                    level.toFloat(),
                    date,
                ),
            )
    }
}
