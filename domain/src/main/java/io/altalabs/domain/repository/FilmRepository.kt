package io.altalabs.domain.repository

import io.altalabs.domain.model.CharacterData
import io.altalabs.domain.model.FilmData
import io.reactivex.Single

interface FilmRepository {
    fun getFilms(): Single<List<FilmData>>
    fun search(query: String): Single<List<CharacterData>>
    fun getCharacterFilmWithId(id: Int): Single<FilmData>
}