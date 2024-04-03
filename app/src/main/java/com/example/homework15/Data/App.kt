package com.example.homework15.Data

import android.app.Application
import androidx.room.Room

class App: Application() {
    lateinit var db: AppDB

    override fun onCreate() {
        super.onCreate()
        db = Room.inMemoryDatabaseBuilder(
            this,
            AppDB::class.java,
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}