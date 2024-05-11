/*
 * ErrorResponse.kt
 * Created by Ulises Gonzalez on 10/05/24
 * Copyright (c) 2024. All rights reserved.
 */
package com.compose.data.network.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("type", alternate = ["httpCode"])
    val type: String?,
    @SerializedName("code", alternate = ["httpMessage"])
    val code: String?,
    @SerializedName("details", alternate = ["moreInformation"])
    val details: String?,
    @SerializedName("moreInfo")
    val moreInfo: String?,
)
