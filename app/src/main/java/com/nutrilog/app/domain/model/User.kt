package com.nutrilog.app.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val gender: Gender,
    val age: Int,
    val token: String,
)

enum class Gender(val label: String) {
    MALE("male"),
    FEMALE("female"),
    OTHER("other"),
}
