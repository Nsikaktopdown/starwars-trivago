package io.altalabs.api.boilerplate.model

import com.squareup.moshi.Json

data class CharacterJson(@Json(name ="name") var name : String,
                     @Json(name ="height") var height: String,
                     @Json(name ="homeworld") var homeworld: String,
                     @Json(name ="birth_year") var birth_year: String,
                     @Json(name ="films") var films: List<String>)

data class  CharacterResponse( @Json(name ="results") var results: List<CharacterJson>)