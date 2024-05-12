/*
 * DeleteInFavoriteUseCase.kt
 * Created by Ulises Gonzalez on 11/05/24
 * Copyright (c) 2024. All rights reserved.
 */
package com.compose.domain.usecase.favorite

import com.compose.domain.model.Character
import com.compose.domain.repository.FavoriteRepository
import javax.inject.Inject

class DeleteInFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
) {
    suspend operator fun invoke(character: Character) =
        favoriteRepository.deleteToFavorite(character)
}
