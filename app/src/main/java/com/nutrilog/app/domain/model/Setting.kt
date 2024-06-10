package com.nutrilog.app.domain.model

enum class Language(val value: String) {
    ENGLISH("en"),
    INDONESIA("in"),
}

enum class ActiveLevel(val value: String){
    ACTIVE("Active"),
    MODERATELY("Moderately Active"),
    SEDENTARY("Sedentary")
}
