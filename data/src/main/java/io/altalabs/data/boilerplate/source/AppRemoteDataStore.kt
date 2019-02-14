package io.altalabs.data.boilerplate.source

import io.altalabs.data.boilerplate.model.FilmEntity
import io.altalabs.data.boilerplate.repository.AppCache
import io.altalabs.data.boilerplate.repository.AppDataStore
import io.altalabs.data.boilerplate.repository.AppRemote
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


/**
 * Implementation of the [AppDataStore] interface to provide a means of communicating
 * with the remote data source
 */
class AppRemoteDataStore @Inject constructor(private val remote: AppRemote) : AppDataStore {
    override fun clearFilms(): Completable {
        throw  UnsupportedOperationException()
    }

    override fun getFilmList(): Single<List<FilmEntity>> {
        throw  UnsupportedOperationException()
    }

    override fun saveFilmList(list: List<FilmEntity>): Completable {
        throw  UnsupportedOperationException()
    }

    override fun fetchFilmList(): Single<List<FilmEntity>> {
        return  remote.getFilms()
    }


}