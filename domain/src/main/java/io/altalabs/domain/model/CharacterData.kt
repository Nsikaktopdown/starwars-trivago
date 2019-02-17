package io.altalabs.domain.model

data class  CharacterData(var name: String,
                          var height: String,
                          var homeworld: String,
                          var birth_year: String,
                          var films: List<String>)