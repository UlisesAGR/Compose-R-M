/*
 * ContainerRepository.kt
 * Created by Ulises Gonzalez on 10/05/24
 * Copyright (c) 2024. All rights reserved.
 */
package com.compose.domain.repository

import com.compose.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun getFavorites(): Flow<List<Character>>
    suspend fun existInFavorites(id: Int): Flow<Character?>
    suspend fun addToFavorite(character: Character): Flow<Unit>
    suspend fun deleteToFavorite(character: Character): Flow<Unit>
}
