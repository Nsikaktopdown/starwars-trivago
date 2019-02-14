package io.altalabs.api.boilerplate.mapper

import io.altalabs.api.boilerplate.model.Film
import io.altalabs.data.boilerplate.model.FilmEntity
import javax.inject.Inject

class FilmMapper @Inject constructor(): Mapper<List<Film>, List<FilmEntity>>{
    override fun mapFromRemote(type: List<Film>): List<FilmEntity> {
        return  type.map { map(it) }
    }

    fun map(type: Film): FilmEntity = FilmEntity(type.episode_id, type.title, type.director, type.release_date, type.opening_crawl, type.producer)
}