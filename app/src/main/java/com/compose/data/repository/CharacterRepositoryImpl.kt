/*
 * ContainerRepositoryImpl.kt
 * Created by Ulises Gonzalez on 10/05/24
 * Copyright (c) 2024. All rights reserved.
 */
package com.compose.data.repository

import com.compose.data.network.utils.Resource
import com.compose.data.source.CharacterDataSource
import com.compose.data.source.FavoriteDataSource
import com.compose.domain.model.Character
import com.compose.domain.repository.CharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterDataSource: CharacterDataSource,
    private val favoriteDataSource: FavoriteDataSource,
    private val dispatcher: CoroutineDispatcher,
) : CharacterRepository {
    override suspend fun getCharacter(id: Int): Flow<Resource<Character>> =
        flow {
            val isExistInFavorite = favoriteDataSource.existInFavorites(id)
            isExistInFavorite?.let { character ->
                emit(Resource.Success(character))
            } ?: run {
                emit(characterDataSource.getCharacter(id))
            }
        }.flowOn(dispatcher)
}
