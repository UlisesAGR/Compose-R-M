package com.compose.model

import javax.annotation.concurrent.Immutable

@Immutable
data class Character(
    val id: Int,
    val image: String?,
    val name: String?,
    val specie: String?,
    val status: Status,
) {
    enum class Status {
        ALIVE,
        DEAD,
        UNKNOWN,
    }
}

private fun String?.toStatus(): Character.Status =
    when (this?.lowercase() ?: "unknown") {
        "alive" -> Character.Status.ALIVE
        "dead" -> Character.Status.DEAD
        else -> Character.Status.UNKNOWN
    }

fun getCharacters() = (1..10).map {
    Character(
        id = it,
        name = "Character $it",
        status = "Alive".toStatus(),
        specie = "Human",
        image = "https://rickandmortyapi.com/api/character/avatar/$it.jpeg",
    )
}
