package com.compose.ui.utils

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.Color
import coil.annotation.ExperimentalCoilApi
import coil.imageLoader
import com.compose.model.Character

fun String?.setValue(): String = this ?: "N/A"

fun setStatusColor(status: Character.Status): Color =
    when (status) {
        Character.Status.ALIVE -> Color(0XFF78BA46)
        Character.Status.DEAD -> Color(0xFFBA1A1A)
        Character.Status.UNKNOWN -> Color(0xFF74796D)
    }

fun Context.nextScreen(screen: ComponentActivity) {
    Intent(this, screen::class.java).apply {
        startActivity(this)
    }
}

@OptIn(ExperimentalCoilApi::class)
fun Context.clearImageCaching() {
    imageLoader.apply {
        diskCache?.clear()
        memoryCache?.clear()
    }
}
