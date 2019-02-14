package io.altalabs.api.boilerplate.model

import com.squareup.moshi.Json

data class FilmsResponse(@Json(name = "results") var results: List<Film>)

data class Film(@Json(name = "episode_id") var episode_id: Int,
                @Json(name = "title") var title: String,
                @Json(name = "director") var director: String,
                @Json(name = "release_date") var release_date: String,
                @Json(name = "opening_crawl") var opening_crawl: String,
                @Json(name = "producer") var producer: String)