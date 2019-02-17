package io.altalabs.data.boilerplate

import io.altalabs.data.boilerplate.mapper.CharacterDataMapper
import io.altalabs.data.boilerplate.mapper.FilmDataMapper
import io.altalabs.data.boilerplate.model.FilmEntity
import io.altalabs.data.boilerplate.source.AppDataStoreFactory
import io.altalabs.domain.model.CharacterData
import io.altalabs.domain.model.FilmData
import io.altalabs.domain.repository.FilmRepository
import io.reactivex.Single
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(private var factory: AppDataStoreFactory,
                                             private val mapper: FilmDataMapper,
                                             private val characterDataMapper: CharacterDataMapper) : FilmRepository {

    /**
     * Get films featured by this character @param[id]
     */
    override fun getCharacterFilmWithId(id: Int): Single<FilmData> {
        return factory.retrieveCacheDataStore().getFilmWithId(id).map {
            FilmData(it.episode_id,
                    it.title,
                    it.director,
                    it.release_date,
                    it.opening_crawl,
                    it.producer)
        }
    }

    /**
     * Search  StarWars character
     */
    override fun search(query: String): Single<List<CharacterData>> {
        return factory.retrieveRemoteDataStore().search(query).map { characterDataMapper.mapToDomain(it) }
    }


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