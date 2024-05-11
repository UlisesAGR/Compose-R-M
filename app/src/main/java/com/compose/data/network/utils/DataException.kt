/*
 * UseCaseException.kt
 * Created by Ulises Gonzalez on 10/05/24
 * Copyright (c) 2024. All rights reserved.
 */
package com.compose.data.network.utils

import com.compose.data.network.utils.DataError.Network

sealed class DataException : kotlin.Exception() {
    class GenericException(
        val code: Int = 0,
        val error: Network,
    )
}
