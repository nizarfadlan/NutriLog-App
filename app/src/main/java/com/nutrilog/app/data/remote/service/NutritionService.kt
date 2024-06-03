package com.nutrilog.app.data.remote.service

import com.nutrilog.app.data.remote.response.NutritionResponse
import com.nutrilog.app.data.remote.response.PredictResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface NutritionService {
    @GET("nutrients")
    suspend fun getNutrients(
        @Query("date") date: String,
    ): NutritionResponse

    @POST("predict")
    @Multipart
    suspend fun predict(
        @Part image: MultipartBody.Part,
    ): PredictResponse
}
