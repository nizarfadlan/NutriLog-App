package com.nutrilog.app.data.remote.response

import com.google.gson.annotations.SerializedName
import com.nutrilog.app.domain.model.User

data class LoginResponse(
    @field:SerializedName("loginResult")
    val loginResult: User,
    @field:SerializedName("error")
    val error: Boolean,
    @field:SerializedName("message")
    val message: String,
)
