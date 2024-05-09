package com.compose.presentation.container

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.compose.presentation.container.navigation.AppNavigation
import com.compose.ui.theme.Screen

class ContainerScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Screen {
                AppNavigation()
            }
        }
    }
}
