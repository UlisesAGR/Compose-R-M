package com.compose.presentation.beginning.container

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.compose.presentation.beginning.navigation.BeginningNavigation
import com.compose.presentation.main.container.ContainerScreen
import com.compose.ui.theme.Screen
import com.compose.ui.utils.nextScreen
import javax.inject.Inject

class BeginningContainer @Inject constructor(

) : ComponentActivity() {
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
