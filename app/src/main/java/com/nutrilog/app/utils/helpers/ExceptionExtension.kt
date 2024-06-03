package com.nutrilog.app.utils.helpers

import com.google.gson.Gson
import com.nutrilog.app.data.remote.response.BasicResponse
import com.nutrilog.app.domain.common.StatusResponse
import retrofit2.HttpException

fun Exception.createResponse(): BasicResponse? {
    return when (this) {
        is HttpException -> {
            Gson().fromJson(response()?.errorBody()?.string(), BasicResponse::class.java)
        }

        else -> {
            BasicResponse(
                status = StatusResponse.ERROR,
                message = this.message ?: "Error",
            )
        }
    }
}
