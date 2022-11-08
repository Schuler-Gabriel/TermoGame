package com.schuler.termogame.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.schuler.termogame.model.GameData
import com.schuler.termogame.model.Word

@Database(entities = [Word::class, GameData::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun appDao(): AppDatabaseDao
}