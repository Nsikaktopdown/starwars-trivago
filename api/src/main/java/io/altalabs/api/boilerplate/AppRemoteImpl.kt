package io.altalabs.api.boilerplate

import io.altalabs.api.boilerplate.mapper.CharacterMapper
import io.altalabs.api.boilerplate.mapper.FilmMapper
import io.altalabs.data.boilerplate.model.CharacterEntity
import io.altalabs.data.boilerplate.model.FilmEntity
import io.altalabs.data.boilerplate.repository.AppRemote
import io.reactivex.Single
import javax.inject.Inject

/**
 * This class implement [AppRemote] interface
 */
class  AppRemoteImpl @Inject constructor(private var api: RemoteApi,
                                         private var mapper: FilmMapper,
                                         private var characterMapper: CharacterMapper): AppRemote{
    override fun search(query: String): Single<List<CharacterEntity>> {
        return  api.search(query).map {  characterMapper.mapFromRemote(it.results) }
    }

    override fun getFilms(): Single<List<FilmEntity>> {
        return  api.getFilms().map { mapper.mapFromRemote(it.results) }
    }


}