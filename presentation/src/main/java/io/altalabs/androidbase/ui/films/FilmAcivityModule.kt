package io.altalabs.androidbase.ui.films

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.altalabs.androidbase.ViewModelKey
import io.altalabs.data.boilerplate.FilmRepositoryImpl
import io.altalabs.domain.repository.FilmRepository

@Module
abstract  class FilmAcivityModule{

    @Binds
    abstract fun bindFilmRespository(filmRepositoryImpl: FilmRepositoryImpl): FilmRepository

    @Binds
    @IntoMap
    @ViewModelKey(FilmActivityViewModel::class)
    internal  abstract  fun filmViewModel(viewModel: FilmActivityViewModel): ViewModel
}