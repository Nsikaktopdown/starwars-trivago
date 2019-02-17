package io.altalabs.data.boilerplate.repository

import io.altalabs.data.boilerplate.model.CharacterEntity
import io.altalabs.data.boilerplate.model.FilmEntity
import io.reactivex.Single

/**
 * Interface defining methods for get films from remote server. This is to be implemented by the
 * api layer, using this interface as a way of communicating.
 */
interface AppRemote{
    fun getFilms(): Single<List<FilmEntity>>
    fun search(query: String): Single<List<CharacterEntity>>
}