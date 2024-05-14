package com.compose.presentation.screen.favorite

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.compose.R
import com.compose.domain.model.Character
import com.compose.presentation.common.ItemCharacter

@Composable
fun FavoriteList(
    characters: List<Character>,
    onCharacterClick: (Character) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = dimensionResource(id = R.dimen.padding))
    ) {
        items(
            items = characters,
            key = { it.id },
        ) { character ->
            ItemCharacter(
                character,
                onCharacterClick = {
                    onCharacterClick(character)
                },
            )
        }
    }
}
