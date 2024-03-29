package io.altalabs.androidbase.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * ViewModelFactory which uses Dagger to create the instances.
 */
class AppViewModelFactory @Inject constructor(
        private val creators: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val found = creators.entries.find { modelClass.isAssignableFrom(it.key) }
        val creator = found?.value
                ?: throw IllegalArgumentException("unknown model class " + modelClass)
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
