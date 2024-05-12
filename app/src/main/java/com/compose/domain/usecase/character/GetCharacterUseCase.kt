/*
 * GetCharacterUseCase.kt
 * Created by Ulises Gonzalez on 10/05/24
 * Copyright (c) 2024. All rights reserved.
 */
package com.compose.domain.usecase.character

import com.compose.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
) {
    suspend operator fun invoke(id: Int) = characterRepository.getCharacter(id)
}
