/*
 * FavoriteRepositoryImpl.kt
 * Created by Ulises Gonzalez on 11/05/24
 * Copyright (c) 2024. All rights reserved.
 */
package com.compose.data.repository

import com.compose.data.source.FavoriteDataSource
import com.compose.domain.model.Character
import com.compose.domain.repository.FavoriteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDataSource: FavoriteDataSource,
    private val dispatcher: CoroutineDispatcher,
) : FavoriteRepository {
    override suspend fun getFavorites(): Flow<List<Character>> =
        favoriteDataSource.getFavorites()
            .flowOn(dispatcher)

    override suspend fun existInFavorites(id: Int) =
        flow {
            emit(favoriteDataSource.existInFavorites(id))
        }.flowOn(dispatcher)

    override suspend fun addToFavorite(character: Character) =
        flow {
            emit(favoriteDataSource.addToFavorite(character))
        }.flowOn(dispatcher)

    override suspend fun deleteToFavorite(character: Character) =
        flow {
            emit(favoriteDataSource.deleteToFavorite(character))
        }.flowOn(dispatcher)
}
