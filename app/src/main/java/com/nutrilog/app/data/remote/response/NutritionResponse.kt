package com.nutrilog.app.data.remote.response

import com.google.gson.annotations.SerializedName
import com.nutrilog.app.domain.common.StatusResponse
import com.nutrilog.app.domain.model.Nutrition

data class NutritionResponse(
    @field:SerializedName("status")
    val status: StatusResponse,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("data")
    val data: List<Nutrition>,
)
