package com.compose.presentation.main.container

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.compose.presentation.main.navigation.AppNavigation
import com.compose.ui.theme.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
