package com.nutrilog.app.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "nutrition")
data class Nutrition(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo("user_id")
    val userId: String,
    @ColumnInfo("food_name")
    val foodName: String,
    val carbohydrate: Float,
    val proteins: Float,
    val fat: Float,
    val calories: Float,
    @ColumnInfo("created_at")
    val createdAt: Date,
)
