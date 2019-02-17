package io.altalabs.api.boilerplate

import io.altalabs.api.boilerplate.model.CharacterResponse
import io.altalabs.api.boilerplate.model.FilmsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApi{
    @GET("films")
    fun getFilms() : Single<FilmsResponse>

    @GET("people/")
    fun search(@Query("search")  query: String): Single<CharacterResponse>
}