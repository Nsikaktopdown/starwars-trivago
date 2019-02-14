package io.altalabs.data.boilerplate.model

import com.squareup.moshi.Json

data class FilmEntity(var episode_id: Int,
                      var title: String,
                      var director: String,
                      var release_date: String,
                      var opening_crawl: String,
                      var producer: String)