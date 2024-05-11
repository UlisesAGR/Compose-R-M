/*
 * ContainerRepositoryImpl.kt
 * Created by Ulises Gonzalez on 10/05/24
 * Copyright (c) 2024. All rights reserved.
 */
package com.compose.data.repository

import com.compose.data.source.CharacterDataSource
import com.compose.domain.repository.CharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterDataSource: CharacterDataSource,
    private val dispatcher: CoroutineDispatcher,
) : CharacterRepository {
    override suspend fun getCharacter(id: Int) =
        flow {
            emit(characterDataSource.getCharacter(id))
        }.flowOn(dispatcher)
}
