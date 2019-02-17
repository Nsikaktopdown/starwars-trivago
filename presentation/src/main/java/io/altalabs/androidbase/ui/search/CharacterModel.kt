package io.altalabs.androidbase.ui.search

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterModel(var name: String,
                          var height: String,
                          var homeworld: String,
                          var birth_year: String,
                          var films: List<String>): Parcelable