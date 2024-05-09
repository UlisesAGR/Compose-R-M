package com.compose.ui.widgets

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

@Composable
fun Confetti() {
    KonfettiView(
        modifier = Modifier.fillMaxSize(),
        parties = listOf(
            Party(
                speed = 0f,
                maxSpeed = 30f,
                damping = 0.9f,
                spread = 360,
                colors = listOf(0x78ba46, 0xb8d551, 0x14b3cb, 0x0d2324),
                position = Position.Relative(0.5, 0.4),
                emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
            )
        ),
    )
}