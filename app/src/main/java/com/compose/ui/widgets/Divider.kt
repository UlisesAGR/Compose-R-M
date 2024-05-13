package com.compose.ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Divider(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier
            .fillMaxWidth()
            .height(0.5.dp)
            .padding(start = 128.dp),
        color = MaterialTheme.colorScheme.outline,
    )
}
