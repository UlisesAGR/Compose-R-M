package com.compose.presentation.main.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.compose.R
import com.compose.ui.widgets.animatedColorText

@Composable
fun EmptyScreen(
    icon: Int,
    message: String,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            modifier = Modifier.size(dimensionResource(id = R.dimen.image_medium)),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = animatedColorText(),
        )
        Text(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)),
            textAlign = TextAlign.Center,
            text = message,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenPreview() {
    EmptyScreen(
        icon = R.drawable.il_logo_words,
        message = stringResource(R.string.example),
    )
}