package io.altalabs.androidbase.ui.films.mapper

import io.altalabs.androidbase.ui.films.FilmModel
import io.altalabs.domain.model.FilmData
import javax.inject.Inject

class FilmMapper @Inject constructor() : Mapper<List<FilmData>, List<FilmModel>> {
    override fun mapFromDomain(type: List<FilmData>): List<FilmModel> {
        return type.map { map(it) }
    }

    fun map(type: FilmData): FilmModel = FilmModel(type.episode_id, type.title, type.director, type.release_date, type.opening_crawl, type.producer)
}