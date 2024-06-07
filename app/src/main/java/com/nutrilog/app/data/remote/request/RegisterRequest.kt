package com.nutrilog.app.data.remote.request

import com.nutrilog.app.domain.model.Gender

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val gender: Gender,
    val age: Int,
)
