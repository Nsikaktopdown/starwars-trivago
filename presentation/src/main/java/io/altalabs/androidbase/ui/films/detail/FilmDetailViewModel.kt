package io.altalabs.androidbase.ui.films.detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.altalabs.androidbase.Data
import io.altalabs.androidbase.DataState
import io.altalabs.androidbase.ui.films.FilmModel
import io.altalabs.domain.model.FilmData
import io.altalabs.domain.usecase.GetFilmById
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class FilmDetailViewModel @Inject constructor(private val useCase: GetFilmById): ViewModel(){
    var film = MutableLiveData<Data<FilmModel>>()


    fun getFilm(id: Int){
        useCase.execute(GetFilmSubscriber(), id)

    }

    inner class GetFilmSubscriber: DisposableSingleObserver<FilmData>(){
        override fun onSuccess(t: FilmData) {
            film.postValue(Data(dataState = DataState.SUCCESS, data = FilmModel(
                    t.episode_id,
                    t.title,
                    t.director,
                    t.release_date,
                    t.opening_crawl,
                    t.producer
            ), message = null))
        }

        override fun onError(e: Throwable) {
            film.postValue(Data(dataState = DataState.ERROR, data = null, message = "Movie Not Found"))
            println(e.message)

        }

    }
}