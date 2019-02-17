package io.altalabs.data.boilerplate.mapper

import io.altalabs.data.boilerplate.model.CharacterEntity
import io.altalabs.domain.model.CharacterData
import javax.inject.Inject

class CharacterDataMapper @Inject constructor(): Mapper<List<CharacterEntity>, List<CharacterData>>{
    override fun mapToDomain(type: List<CharacterEntity>): List<CharacterData> {
        return type.map { map(it) }
    }
    fun map(type:CharacterEntity): CharacterData = CharacterData(type.name, type.height, type.homeworld, type.birth_year, type.films)
}