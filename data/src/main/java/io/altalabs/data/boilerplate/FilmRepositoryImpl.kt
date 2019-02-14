package io.altalabs.data.boilerplate

import io.altalabs.data.boilerplate.mapper.FilmDataMapper
import io.altalabs.data.boilerplate.model.FilmEntity
import io.altalabs.data.boilerplate.source.AppDataStoreFactory
import io.altalabs.domain.model.FilmData
import io.altalabs.domain.repository.FilmRepository
import io.reactivex.Single
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(private var factory: AppDataStoreFactory,
                                             private val mapper: FilmDataMapper) : FilmRepository {


    override fun getFilms(): Single<List<FilmData>> {
        return factory.retrieveCacheDataStore().getFilmList().flatMap {
            if (it.isEmpty()) {
                fetchMovieFromRemote()
            } else Single.just(it)
        }.map {
            mapper.mapToDomain(it)
        }
    }

    private fun fetchMovieFromRemote(): Single<List<FilmEntity>> {
        return factory.retrieveRemoteDataStore().fetchFilmList().doOnSuccess {
            kotlin.run {
                //clear films
                factory.retrieveCacheDataStore().clearFilms().subscribe()
                //save films
                factory.retrieveCacheDataStore().saveFilmList(it).subscribe()
            }
        }
    }


}