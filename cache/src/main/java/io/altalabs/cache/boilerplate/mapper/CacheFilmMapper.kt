package io.altalabs.cache.boilerplate.mapper

import io.altalabs.cache.boilerplate.entity.CacheFilmEntity
import io.altalabs.data.boilerplate.model.FilmEntity
import javax.inject.Inject

class CacheFilmMapper @Inject constructor() : CacheMapper<List<FilmEntity>, List<CacheFilmEntity>> {
    override fun mapFromCache(type: List<CacheFilmEntity>): List<FilmEntity> {
        return type.map { map(it) }
    }

    override fun mapFromData(type: List<FilmEntity>): List<CacheFilmEntity> {
        return type.map { map(it) }
    }

    fun map(type: CacheFilmEntity): FilmEntity = FilmEntity(type.episode_id, type.title, type.director, type.release_date, type.opening_crawl, type.producer)
    fun map(type: FilmEntity): CacheFilmEntity = CacheFilmEntity(type.episode_id, type.title, type.director, type.release_date, type.opening_crawl, type.producer)
}