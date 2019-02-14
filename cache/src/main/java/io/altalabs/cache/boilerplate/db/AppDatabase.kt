package io.altalabs.cache.boilerplate.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import io.altalabs.cache.boilerplate.entity.CacheFilmEntity

@Database(entities = arrayOf(CacheFilmEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}