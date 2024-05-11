package com.compose.ui.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.Color
import coil.annotation.ExperimentalCoilApi
import coil.imageLoader
import com.compose.data.network.utils.DataError.Launch
import com.compose.data.network.utils.Result
import com.compose.domain.model.Character.Status

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}


fun String?.setValue(): String = this ?: "N/A"

fun setStatusColor(status: Status): Color =
    when (status) {
        Status.ALIVE -> Color(0XFF78BA46)
        Status.DEAD -> Color(0xFFBA1A1A)
        Status.UNKNOWN -> Color(0xFF74796D)
    }

fun Context.nextScreen(screen: ComponentActivity) {
    Intent(this, screen::class.java).apply {
        startActivity(this)
    }
}

fun Context.launchWeb(url: String): Result<Unit, Launch> {
    val intent = Intent(Intent.ACTION_VIEW)
    return if (intent.resolveActivity(this.packageManager) != null) {
        startActivity(
            intent.also {
                it.data = Uri.parse(url)
            }
        )
        Result.Success(Unit)
    } else {
        Result.Error(Launch.UNKNOWN)
    }
}

@OptIn(ExperimentalCoilApi::class)
fun Context.clearImageCaching() {
    imageLoader.apply {
        diskCache?.clear()
        memoryCache?.clear()
    }
}
