package com.compose.domain.model

import com.compose.data.local.entity.CharacterEntity
import com.compose.data.network.response.CharacterResponse
import javax.annotation.concurrent.Immutable

@Immutable
data class Character(
    val id: Int,
    val name: String?,
    val status: Status,
    val specie: String?,
    val image: String?,
) {
    enum class Status {
        ALIVE,
        DEAD,
        UNKNOWN,
    }
}

fun CharacterEntity.toDomain() =
    Character(id, name, status.toStatus(), specie, image)

fun CharacterResponse.toEntity() =
    CharacterEntity(id, name, status, specie, image)

fun CharacterResponse.toDomain() =
    Character(id, name, status.toStatus(), specie, image)

private fun String.toStatus(): Character.Status =
    when (this.lowercase()) {
        "alive" -> Character.Status.ALIVE
        "dead" -> Character.Status.DEAD
        else -> Character.Status.UNKNOWN
    }

fun getCharacters() = (1..10).map {
    Character(
        id = it,
        name = "Character $it",
        status = Character.Status.ALIVE,
        specie = "Human",
        image = "https://rickandmortyapi.com/api/character/avatar/$it.jpeg",
    )
}
