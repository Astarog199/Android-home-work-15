package com.example.homework15.Data

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey


data class Word(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "word") val word: String,
    @ColumnInfo(name = "counter") val counter: Int
)
