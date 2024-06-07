package com.nutrilog.app.utils.helpers

import android.util.Patterns
import com.nutrilog.app.domain.model.Gender
import com.nutrilog.app.utils.constant.AppConstant

val String.isEmailCorrect: Boolean
    get() = Patterns.EMAIL_ADDRESS.matcher(this).matches()

val String.isFullNameCorrect: Boolean
    get() = !Regex("[^a-zA-Z ]").containsMatchIn(this)

val String.isLengthPasswordCorrect: Boolean
    get() = this.length >= AppConstant.PASSWORD_LENGTH

fun String.convertGender(): Gender {
    return when (this.lowercase()) {
        "male" -> Gender.MALE
        "female" -> Gender.FEMALE
        else -> throw IllegalArgumentException("Unknown gender: $this")
    }
}
