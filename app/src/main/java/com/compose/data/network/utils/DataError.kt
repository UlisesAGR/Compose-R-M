package com.compose.data.network.utils

sealed interface DataError : Error {
    enum class Network : Error {
        RED_ERROR,
        TIMEOUT_ERROR,
        HTTP_ERROR,
        GENERIC_ERROR,
    }

    enum class Launch : Error {
        UNKNOWN,
    }

    object Constants {
        const val GENERIC_ERROR_HTTP = "Hay problemas de comunicacion con el servidor"
        const val EMPTY_BODY_RESPONSE_CODE = "emptyBody"
        const val JSON_EXCEPTION = "JSON value is not valid for this object: "
    }
}
