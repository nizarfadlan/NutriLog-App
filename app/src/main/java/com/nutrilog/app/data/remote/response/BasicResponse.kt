package com.nutrilog.app.data.remote.response

import com.google.gson.annotations.SerializedName
import com.nutrilog.app.domain.common.StatusResponse

data class BasicResponse(
    @field:SerializedName("status")
    val status: StatusResponse,
    @field:SerializedName("message")
    val message: String,
)
