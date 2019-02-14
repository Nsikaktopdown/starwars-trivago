package io.altalabs.cache.boilerplate.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "starWarsFilms")
data class CacheFilmEntity(@PrimaryKey var episode_id: Int,
                           var title: String,
                           var director: String,
                           var release_date: String,
                           var opening_crawl: String,
                           var producer: String)