package com.nutrilog.app.domain.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: String,
    val name: String,
    val email: String,
    val gender: Gender,
    @field:SerializedName("date_of_birth")
    val dateOfBirth: String,
    val token: String,
)

enum class Gender(val label: String) {
    MALE("male"),
    FEMALE("female"),
    OTHER("other"),
}
