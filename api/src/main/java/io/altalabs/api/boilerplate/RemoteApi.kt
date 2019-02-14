package io.altalabs.api.boilerplate

import io.altalabs.api.boilerplate.model.FilmsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface RemoteApi{
    @GET("films")
    fun getFilms() : Single<FilmsResponse>
}