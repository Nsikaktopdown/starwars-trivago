package io.altalabs.androidbase.di

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.altalabs.androidbase.ui.films.FilmListActivity
import io.altalabs.androidbase.ui.films.FilmAcivityModule
import io.altalabs.androidbase.ui.films.detail.FilmDetailActivity
import io.altalabs.androidbase.ui.films.detail.FilmDetailModule
import io.altalabs.androidbase.ui.search.SearchActivity
import io.altalabs.androidbase.ui.search.SearchActivityModule
import io.altalabs.domain.executor.PostExecutionThread

@Module
abstract class ActivityBindingModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: io.altalabs.androidbase.UiThread): PostExecutionThread

    @ActivityScoped
    @ContributesAndroidInjector(modules = [FilmAcivityModule::class])
    internal abstract fun filmActivity(): FilmListActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(FilmAcivityModule::class, SearchActivityModule::class))
    internal  abstract  fun searchActivity(): SearchActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(FilmDetailModule::class, FilmAcivityModule::class))
    internal  abstract  fun filmDetailAciviy(): FilmDetailActivity
}
