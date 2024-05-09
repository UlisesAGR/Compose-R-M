package com.compose.presentation.beginning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.compose.presentation.beginning.navigation.BeginningNavigation
import com.compose.presentation.container.ContainerScreen
import com.compose.ui.theme.Screen
import com.compose.ui.utils.nextScreen

class BeginningContainer : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        splash.setKeepOnScreenCondition { false }
        enableEdgeToEdge()
        setContent {
            Screen {
                BeginningNavigation {
                    nextScreen(ContainerScreen())
                    finish()
                }
            }
        }
    }
}
