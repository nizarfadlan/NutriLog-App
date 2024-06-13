package com.nutrilog.app.data.remote

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.nutrilog.app.domain.common.StatusResponse
import com.nutrilog.app.domain.model.Gender
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GenderAdapter : JsonDeserializer<Gender>, JsonSerializer<Gender> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext,
    ): Gender? {
        return enumValues<Gender>().firstOrNull { it.name.equals(json.asString, ignoreCase = true) }
    }

    override fun serialize(
        src: Gender,
        typeOfSrc: Type,
        context: JsonSerializationContext,
    ): JsonElement {
        return JsonPrimitive(src.name)
    }
}

class DateAdapter : JsonDeserializer<Date>, JsonSerializer<Date> {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext,
    ): Date? {
        return try {
            dateFormat.parse(json.asString)
        } catch (e: Exception) {
            null
        }
    }

    override fun serialize(
        src: Date,
        typeOfSrc: Type,
        context: JsonSerializationContext,
    ): JsonElement {
        return JsonPrimitive(dateFormat.format(src))
    }
}

class ResponseStatusAdapter : JsonDeserializer<StatusResponse>, JsonSerializer<StatusResponse> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext,
    ): StatusResponse? {
        return enumValues<StatusResponse>().firstOrNull {
            it.name.equals(
                json.asString,
                ignoreCase = true,
            )
        }
    }

    override fun serialize(
        src: StatusResponse,
        typeOfSrc: Type,
        context: JsonSerializationContext,
    ): JsonElement {
        return JsonPrimitive(src.name)
    }
}
