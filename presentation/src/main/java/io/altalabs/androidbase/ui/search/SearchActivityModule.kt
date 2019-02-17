package io.altalabs.androidbase.ui.search

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.altalabs.androidbase.ViewModelKey
import io.altalabs.androidbase.ui.films.FilmActivityViewModel

@Module
abstract class SearchActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchActivityViewModel::class)
    internal abstract fun searchViewModel(viewModel: SearchActivityViewModel): ViewModel
}