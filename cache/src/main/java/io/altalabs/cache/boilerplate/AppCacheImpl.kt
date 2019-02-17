package io.altalabs.cache.boilerplate

import io.altalabs.cache.boilerplate.db.AppDatabase
import io.altalabs.cache.boilerplate.mapper.CacheFilmMapper
import io.altalabs.data.boilerplate.model.FilmEntity
import io.altalabs.data.boilerplate.repository.AppCache
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Cache Implementation for storing and retrieving data in cache
 * This class implement the [AppCache] from the Data layer as it is for defining the
 * operations in which data store implementation layers can carry out.
 */

class AppCacheImpl @Inject constructor(private var database: AppDatabase,
                                       private var mapper: CacheFilmMapper) : AppCache {
    override fun getFilmWithId(id: Int): Single<FilmEntity> {
        return database.filmDao().getFilmWithId(id).map {
            FilmEntity(
                    it.episode_id,
                    it.title,
                    it.director,
                    it.release_date,
                    it.opening_crawl,
                    it.producer
            )
        }
    }

    override fun saveFilms(list: List<FilmEntity>): Completable {
        return Completable.fromAction { database.filmDao().saveFilms(mapper.mapFromData(list)) }
    }

    override fun getFilms(): Single<List<FilmEntity>> {
        return database.filmDao().getFilms().map { mapper.mapFromCache(it) }
    }

    override fun clearFilms(): Completable {
        return Completable.fromAction { database.filmDao().clearFilms() }
    }


}