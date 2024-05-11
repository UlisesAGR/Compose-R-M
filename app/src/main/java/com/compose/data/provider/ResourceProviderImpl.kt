/*
 * ResourceProviderImpl.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2024. All rights reserved.
 */
package com.compose.data.provider

import android.content.Context
import com.compose.R
import com.compose.domain.provider.ResourceProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(
    @ApplicationContext appContext: Context,
): ResourceProvider {
    private val resource = appContext.resources

    override fun getErrorGettingCharacterLabel(): String =
        resource.getString(R.string.app_error_getting_character)
}
