package io.altalabs.data.boilerplate.repository

import io.altalabs.data.boilerplate.model.CharacterEntity
import io.altalabs.data.boilerplate.model.FilmEntity
import io.reactivex.Completable
import io.reactivex.Single

interface AppDataStore {
    fun getFilmList(): Single<List<FilmEntity>>
    fun saveFilmList(list: List<FilmEntity>): Completable
    fun fetchFilmList(): Single<List<FilmEntity>>
    fun clearFilms(): Completable
    fun search(query: String): Single<List<CharacterEntity>>
    fun getFilmWithId(id: Int): Single<FilmEntity>
}