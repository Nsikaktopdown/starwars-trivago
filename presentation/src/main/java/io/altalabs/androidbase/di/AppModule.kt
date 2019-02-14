package io.altalabs.androidbase.di

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import dagger.Module
import dagger.Provides
import io.altalabs.androidbase.App
import io.altalabs.cache.boilerplate.AppCacheImpl
import io.altalabs.cache.boilerplate.db.AppDatabase
import io.altalabs.data.boilerplate.executor.JobExecutor
import io.altalabs.data.boilerplate.repository.AppCache
import io.altalabs.domain.executor.ThreadExecutor
import javax.inject.Singleton


@Module
open class AppModule{

    @Provides
    @Singleton
    fun provideContext(app: App): Context = app.applicationContext

    @Provides
    @Singleton
    fun providesAppDatabase(app: App) : AppDatabase{
        return Room.databaseBuilder(app.applicationContext, AppDatabase::class.java, "trivago_test_db").build()
    }

    @Provides
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    fun provideAppCache(appCacheImpl: AppCacheImpl): AppCache = appCacheImpl


}