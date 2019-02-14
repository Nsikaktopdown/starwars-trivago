package io.altalabs.data.boilerplate.mapper

import io.altalabs.data.boilerplate.model.FilmEntity
import io.altalabs.domain.model.FilmData
import javax.inject.Inject

class  FilmDataMapper @Inject constructor(): Mapper<List<FilmEntity>, List<FilmData>>{
    override fun mapToDomain(type: List<FilmEntity>): List<FilmData> {
       return  type.map { map(it) }
    }
    fun map(type: FilmEntity): FilmData= FilmData(type.episode_id, type.title, type.director, type.release_date, type.opening_crawl, type.producer)
}