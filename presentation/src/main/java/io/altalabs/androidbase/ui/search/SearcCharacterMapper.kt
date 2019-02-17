package io.altalabs.androidbase.ui.search

import io.altalabs.androidbase.ui.films.mapper.Mapper
import io.altalabs.data.boilerplate.model.CharacterEntity
import io.altalabs.domain.model.CharacterData
import javax.inject.Inject

class SearcCharacterMapper @Inject constructor(): Mapper<List<CharacterData>, List<CharacterModel>>{
    override fun mapFromDomain(type: List<CharacterData>): List<CharacterModel> {
       return  type.map { map(it) }
    }

    fun map(type: CharacterData): CharacterModel = CharacterModel(type.name, type.height, type.homeworld, type.birth_year, type.films)
}