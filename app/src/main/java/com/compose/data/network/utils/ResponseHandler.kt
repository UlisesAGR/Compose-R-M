/*
 * ResponseHandler.kt
 * Created by Ulises Gonzalez on 10/05/24
 * Copyright (c) 2024. All rights reserved.
 */
package com.compose.data.network.utils

import com.compose.data.network.response.ErrorResponse
import com.compose.data.network.utils.DataError.Constants.EMPTY_BODY_RESPONSE_CODE
import com.compose.data.network.utils.DataError.Constants.JSON_EXCEPTION
import com.compose.domain.model.FailureData
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import com.compose.data.network.utils.DataError.Constants.GENERIC_ERROR_HTTP as GENERIC_ERROR_HTTP1

fun <R, T> Response<R>.toResult(action: R.() -> T) = toResource(this, action)

private fun <R, T> toResource(
    it: Response<R>,
    action: R.() -> T,
): Resource<T> =
    if (it.isSuccessful) {
        it.body()?.run {
            Resource.Success(action())
        } ?: Resource.Failure(
            status = it.code(),
            stringCode = EMPTY_BODY_RESPONSE_CODE,
            details = GENERIC_ERROR_HTTP1,
        )
    } else {
        val (stringCode, type, detail, moreInfo) = parseFailureResponse(it.code(), it.errorBody())
        Resource.Failure(
            status = it.code(),
            stringCode = stringCode,
            details = detail,
            moreInfo = moreInfo,
            type = type,
        )
    }

internal fun parseFailureResponse(
    errorCode: Int,
    errorResponseBody: ResponseBody?,
): FailureData {
    var stringCode: String = errorCode.toString()
    var detail: String = GENERIC_ERROR_HTTP1
    var moreInfo: String? = null
    var type: String? = null
    val errorBody: String? = errorResponseBody?.toString()
    errorBody?.let {
        try {
            val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
            errorResponse.type?.let {
                type = it
            }
            errorResponse.code?.let {
                stringCode = it
            }
            errorResponse.details?.let {
                detail = it
            }
            moreInfo = errorResponse.moreInfo
        } catch (exception: java.lang.Exception) {
            detail = exception.message ?: (JSON_EXCEPTION + ErrorResponse::class.java)
        }
    }
    return FailureData(stringCode, type, detail, moreInfo)
}

fun Throwable.parseError(): DataException.GenericException =
    when (this) {
        is UnknownHostException, is ConnectException, is SocketException ->
            DataException.GenericException(this.cause.hashCode(), DataError.Network.RED_ERROR)

        is SocketTimeoutException, is TimeoutException ->
            DataException.GenericException(this.cause.hashCode(), DataError.Network.TIMEOUT_ERROR)

        is HttpException, is IOException ->
            DataException.GenericException(this.cause.hashCode(), DataError.Network.HTTP_ERROR)

        else ->
            DataException.GenericException(this.cause.hashCode(), DataError.Network.GENERIC_ERROR)
    }
