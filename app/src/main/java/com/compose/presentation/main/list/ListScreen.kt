package com.compose.presentation.main.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.R
import com.compose.domain.model.Character
import com.compose.domain.model.getCharacters

@Composable
fun ListScreen(
    padding: PaddingValues,
    characterSelected: (Character) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
    ) {
        Image(
            modifier = Modifier
                .width(180.dp)
                .height(80.dp),
            painter = painterResource(id = R.drawable.il_logo_words),
            contentDescription = stringResource(id = R.string.logo_words),
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = dimensionResource(id = R.dimen.padding),
                end = dimensionResource(id = R.dimen.padding),
                bottom = dimensionResource(id = R.dimen.padding),
            ),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding)),
        ) {
            items(items = getCharacters()) { character ->
                ItemCharacter(
                    character,
                    onClick = {
                        println("click")
                        characterSelected(character)
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewListScreen() {
    //ListScreen(PaddingValues(), characterSelected = {}, event = {})
}
