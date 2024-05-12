package com.compose.domain.model

import com.compose.data.local.entity.CharacterFavoriteEntity
import com.compose.data.network.response.CharacterResponse
import javax.annotation.concurrent.Immutable

@Immutable
data class Character(
    val id: Int,
    val name: String?,
    val status: String?,
    val specie: String?,
    val image: String?,
)

fun Character.toEntity() =
    CharacterFavoriteEntity(id, name, status, specie, image)

fun CharacterResponse.toDomain() =
    Character(id, name, status, specie, image)

fun CharacterFavoriteEntity.toDomain() =
    Character(id, name, status, specie, image)

fun getCharacters() = (1..10).map {
    Character(
        id = it,
        name = "Character $it",
        status = "ALIVE",
        specie = "Human",
        image = "https://rickandmortyapi.com/api/character/avatar/$it.jpeg",
    )
}
