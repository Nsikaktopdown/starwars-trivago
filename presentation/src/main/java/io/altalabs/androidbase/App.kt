package io.altalabs.androidbase

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.altalabs.androidbase.di.DaggerAppComponent

class App : DaggerApplication() {


    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}