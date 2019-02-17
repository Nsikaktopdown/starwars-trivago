package io.altalabs.api.boilerplate.mapper

import io.altalabs.api.boilerplate.model.CharacterJson
import io.altalabs.data.boilerplate.model.CharacterEntity
import javax.inject.Inject

class  CharacterMapper @Inject constructor(): Mapper<List<CharacterJson>, List<CharacterEntity> >{
    override fun mapFromRemote(type: List<CharacterJson>): List<CharacterEntity> {
        return type.map { map(it) }
    }

    fun map(type: CharacterJson): CharacterEntity = CharacterEntity(type.name, type.height, type.homeworld, type.birth_year, type.films)

}