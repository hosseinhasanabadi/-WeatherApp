package com.example.weatherapp.model

import android.annotation.SuppressLint
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "settings_tbl")
data class Unit(
    @SuppressLint("KotlinNullnessAnnotation") @NonNull
    @PrimaryKey
    @ColumnInfo(name = "unit")
    val unit:String
)
