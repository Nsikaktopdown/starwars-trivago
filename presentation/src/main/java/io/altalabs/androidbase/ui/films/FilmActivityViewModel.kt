package io.altalabs.androidbase.ui.films

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.altalabs.androidbase.Data
import io.altalabs.androidbase.DataState
import io.altalabs.androidbase.ui.films.mapper.FilmMapper
import io.altalabs.domain.model.FilmData
import io.altalabs.domain.usecase.GetFilms
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class FilmActivityViewModel @Inject constructor(private val useCase: GetFilms,
                                                private val mapper: FilmMapper) : ViewModel() {

    val film = MutableLiveData<Data<List<FilmModel>>>()
    val TAG = FilmActivityViewModel::class.java.simpleName


    fun getFilms() {
       film.postValue(Data(dataState = DataState.LOADING, data = null, message = ""))
        useCase.execute(GetFilmsSubscriber())
    }

    inner class GetFilmsSubscriber : DisposableSingleObserver<List<FilmData>>() {
        override fun onSuccess(t: List<FilmData>) {
            film.postValue(Data(dataState = DataState.SUCCESS, data = mapper.mapFromDomain(t), message = ""))
        }

        override fun onError(e: Throwable) {
            film.postValue(Data(dataState = DataState.ERROR, data = null, message = "Failed to retrieve Recipe"))
            Log.e(TAG, e.message)
        }

    }

}