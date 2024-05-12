package com.compose.presentation.main.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.compose.R
import com.compose.domain.model.Character
import com.compose.presentation.main.list.ItemCharacter

@Composable
fun FavoriteList(
    characters: List<Character>,
    characterSelected: (Character) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = dimensionResource(id = R.dimen.padding),
            end = dimensionResource(id = R.dimen.padding),
            bottom = dimensionResource(id = R.dimen.padding),
        ),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding)),
    ) {
        items(
            items = characters,
            key = { it.id },
        ) { character ->
            ItemCharacter(
                character,
                onClick = {
                    characterSelected(character)
                },
            )
        }
    }
}
