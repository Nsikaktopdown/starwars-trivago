package io.altalabs.androidbase.ui.films.detail

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.altalabs.androidbase.ViewModelKey
import io.altalabs.androidbase.ui.search.SearchActivityViewModel

@Module
abstract class  FilmDetailModule{

    @Binds
    @IntoMap
    @ViewModelKey(FilmDetailViewModel::class)
    internal abstract fun filmDetailViewModel(viewModel: FilmDetailViewModel): ViewModel
}