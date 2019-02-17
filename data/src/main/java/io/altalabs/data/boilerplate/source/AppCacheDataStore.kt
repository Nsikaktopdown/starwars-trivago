package io.altalabs.data.boilerplate.source

import io.altalabs.data.boilerplate.model.CharacterEntity
import io.altalabs.data.boilerplate.model.FilmEntity
import io.altalabs.data.boilerplate.repository.AppCache
import io.altalabs.data.boilerplate.repository.AppDataStore
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [AppDataStore] interface to provide a means of communicating
 * with the local data source
 */
class AppCacheDataStore @Inject constructor(private val cache: AppCache) : AppDataStore {
    override fun getFilmWithId(id: Int): Single<FilmEntity> {
        return  cache.getFilmWithId(id)
    }

    override fun search(query: String): Single<List<CharacterEntity>> {
        throw  UnsupportedOperationException()
    }

    override fun clearFilms(): Completable {
        return  cache.clearFilms()
    }

    override fun getFilmList(): Single<List<FilmEntity>> {
        return cache.getFilms()
    }

    override fun saveFilmList(list: List<FilmEntity>): Completable {
      return  cache.saveFilms(list)
    }

    override fun fetchFilmList(): Single<List<FilmEntity>> {
        throw  UnsupportedOperationException()
    }

}