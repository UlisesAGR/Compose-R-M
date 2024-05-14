package com.compose.presentation.screen.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
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
import com.compose.presentation.common.ItemCharacter
import com.compose.ui.widgets.animatedColorText

@Composable
fun ListScreen(
    innerPadding: PaddingValues,
    onCharacterClick: (Character) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        Icon(
            modifier = Modifier
                .width(135.dp)
                .height(65.dp),
            painter = painterResource(id = R.drawable.il_logo_words),
            contentDescription = stringResource(id = R.string.logo_words),
            tint = animatedColorText(),
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = dimensionResource(id = R.dimen.padding))
        ) {
            items(
                items = getCharacters(),
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
}

@Preview(showBackground = true)
@Composable
private fun PreviewListScreen() {
    ListScreen(PaddingValues(), onCharacterClick = {})
}
