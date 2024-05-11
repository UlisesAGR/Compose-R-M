/*
 * ResourceProviderImpl.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2024. All rights reserved.
 */
package com.compose.data.provider

import android.content.Context
import com.compose.R
import com.compose.data.network.utils.DataError
import com.compose.domain.provider.ResourceProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(
    @ApplicationContext appContext: Context,
) : ResourceProvider {
    private val resource = appContext.resources

    override fun errorGetCharacterLabel(): String =
        resource.getString(R.string.error_getting_character)

    override fun errorRedLabel(): String =
        resource.getString(R.string.error_red)

    override fun errorTimeoutLabel(): String =
        resource.getString(R.string.error_timeout)

    override fun errorHttpLabel(): String =
        resource.getString(R.string.error_http)

    override fun errorGenericLabel(): String =
        resource.getString(R.string.error_generic)

    override fun parseError(network: DataError.Network): String =
        when (network) {
            DataError.Network.RED_ERROR -> errorRedLabel()
            DataError.Network.TIMEOUT_ERROR -> errorRedLabel()
            DataError.Network.HTTP_ERROR -> errorRedLabel()
            DataError.Network.GENERIC_ERROR -> errorRedLabel()
        }
}
