package io.altalabs.androidbase.ui.films

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmModel(var episode_id: Int,
                     var title: String,
                     var director: String,
                     var release_date: String,
                     var opening_crawl: String,
                     var producer: String): Parcelable