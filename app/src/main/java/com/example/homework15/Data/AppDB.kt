package com.example.homework15.Data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Dictionary::class], version = 1)

abstract class AppDB:RoomDatabase() {
    abstract fun dictionaryDao(): DictionaryDao
}