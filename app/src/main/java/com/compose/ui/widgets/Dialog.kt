package com.compose.ui.widgets

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.compose.R

@Composable
fun Dialog(
    modifier: Modifier = Modifier,
    isShow: Boolean,
    icon: ImageVector,
    text: String,
    onConfirmation: () -> Unit,
) {
    if (isShow) {
        AlertDialog(
            modifier = modifier,
            icon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                )
            },
            title = {
                Text(text = stringResource(R.string.message))
            },
            text = {
                Text(text = text)
            },
            onDismissRequest = {
                onConfirmation()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmation()
                    }
                ) {
                    Text(stringResource(R.string.confirm))
                }
            },
        )
    }
}
