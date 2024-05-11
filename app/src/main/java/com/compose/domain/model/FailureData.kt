/*
 * FailureData.kt
 * Created by Ulises Gonzalez on 10/05/24
 * Copyright (c) 2024. All rights reserved.
 */
package com.compose.domain.model

data class FailureData(
    val message: String?,
    val type: String?,
    val detail: String?,
    val moreInfo: String?,
)
