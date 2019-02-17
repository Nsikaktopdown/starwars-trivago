package io.altalabs.data.boilerplate.repository

import io.altalabs.data.boilerplate.model.FilmEntity
import io.reactivex.Completable
import io.reactivex.Single

interface AppCache {
    fun saveFilms(list: List<FilmEntity>): Completable
    fun getFilms(): Single<List<FilmEntity>>
    fun clearFilms(): Completable
    fun getFilmWithId(id: Int): Single<FilmEntity>
}