package io.altalabs.androidbase.di

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.altalabs.androidbase.ui.films.FilmListActivity
import io.altalabs.androidbase.ui.films.FilmAcivityModule
import io.altalabs.domain.executor.PostExecutionThread

@Module
abstract class ActivityBindingModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: io.altalabs.androidbase.UiThread): PostExecutionThread

    @ActivityScoped
    @ContributesAndroidInjector(modules = [FilmAcivityModule::class])
    internal abstract fun filmActivity(): FilmListActivity
}
