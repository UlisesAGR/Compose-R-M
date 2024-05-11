/*
 * CharacterDataSource.kt
 * Created by Ulises Gonzalez on 02/05/24
 * Copyright (c) 2024. All rights reserved.
 */
package com.compose.data.source

import com.compose.data.network.service.CharacterServices
import com.compose.data.network.utils.toResult
import com.compose.domain.model.toDomain
import javax.inject.Inject

class CharacterDataSource @Inject constructor(
    private val characterServices: CharacterServices,
) {
    suspend fun getCharacter(id: Int) =
        characterServices.getCharacter(id).toResult { toDomain() }
}
