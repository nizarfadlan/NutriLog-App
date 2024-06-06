package com.nutrilog.app.data.remote.service

import com.nutrilog.app.data.remote.request.SaveNutritionRequest
import com.nutrilog.app.data.remote.response.NutritionResponse
import com.nutrilog.app.data.remote.response.PredictResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NutritionService {
    @GET("nutrients")
    suspend fun getNutrients(
        @Query("date") date: String,
    ): NutritionResponse

    @POST("analyze")
    suspend fun analyze(
        @Body request: SaveNutritionRequest,
    ): PredictResponse
}
