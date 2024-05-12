/*
 * FavoriteDataSource.kt
 * Created by Ulises Gonzalez on 11/05/24
 * Copyright (c) 2024. All rights reserved.
 */
package com.compose.data.source

import com.compose.data.local.dao.FavoriteDao
import com.compose.domain.model.Character
import com.compose.domain.model.toDomain
import com.compose.domain.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteDataSource @Inject constructor(
    private val favoriteDao: FavoriteDao,
) {
    fun getFavorites(): Flow<List<Character>> =
        favoriteDao.getFavorites().map { list ->
            list.map { character ->
                character.toDomain()
            }
        }

    suspend fun existInFavorites(id: Int) =
        favoriteDao.existInFavorites(id)?.toDomain()

    suspend fun addToFavorite(character: Character) =
        favoriteDao.addToFavorite(character.toEntity())

    suspend fun deleteToFavorite(character: Character) =
        favoriteDao.deleteToFavorite(character.toEntity())
}
