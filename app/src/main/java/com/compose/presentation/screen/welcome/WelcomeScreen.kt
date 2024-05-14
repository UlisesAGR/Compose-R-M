package com.compose.presentation.screen.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.compose.R
import com.compose.ui.theme.typography
import com.compose.ui.widgets.Confetti
import com.compose.ui.widgets.LoadImageBig
import com.compose.ui.widgets.PrimaryButton

@Composable
fun WelcomeScreen(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues()),
    ) {
        Confetti()
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = dimensionResource(id = R.dimen.padding)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_big)),
        ) {
            LoadImageBig(image = R.drawable.il_logo)
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.app_title),
                style = typography.titleLarge,
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.here_you_can_see_content_about_rick_y_morty),
                style = typography.labelSmall,
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_small)))
            PrimaryButton(stringResource(R.string.continue_text)) {
                onClick()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewWelcomeScreen() {
    WelcomeScreen(onClick = {})
}