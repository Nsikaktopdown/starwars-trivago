package io.altalabs.data.boilerplate.model

import com.squareup.moshi.Json

data class CharacterEntity(var name: String,
                           var height: String,
                           var homeworld: String,
                           var birth_year: String,
                           var films: List<String>)
