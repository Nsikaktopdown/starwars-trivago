package io.altalabs.domain.repository

import io.altalabs.domain.model.FilmData
import io.reactivex.Single

interface FilmRepository {
    fun getFilms(): Single<List<FilmData>>
}