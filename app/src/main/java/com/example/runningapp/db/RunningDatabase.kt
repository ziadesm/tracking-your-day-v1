package com.example.runningapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [RunObject::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RunningDatabase: RoomDatabase() {
    abstract fun getRunDao(): RunDao
}