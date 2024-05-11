/*
 * ContainerRepository.kt
 * Created by Ulises Gonzalez on 10/05/24
 * Copyright (c) 2024. All rights reserved.
 */
package com.compose.domain.repository

import com.compose.data.network.utils.Resource
import com.compose.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacter(id: Int): Flow<Resource<Character>>
}
