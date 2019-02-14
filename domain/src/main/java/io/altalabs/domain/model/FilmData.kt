package io.altalabs.domain.model

data class FilmData(var episode_id: Int,
                    var title: String,
                    var director: String,
                    var release_date: String,
                    var opening_crawl: String,
                    var producer: String)