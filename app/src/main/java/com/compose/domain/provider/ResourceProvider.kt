/*
 * ResourceProvider.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2024. All rights reserved.
 */
package com.compose.domain.provider

import com.compose.data.network.utils.DataError

interface ResourceProvider {
    fun errorGetCharacterLabel(): String
    fun errorRedLabel(): String
    fun errorTimeoutLabel(): String
    fun errorHttpLabel(): String
    fun errorGenericLabel(): String
    fun parseError(network: DataError.Network): String
}
