package com.example.mycounterapp.data.model

import retrofit2.Response

sealed class ApiResponse<T>

/**
 * separate class for HTTP 204 responses so that we can make ApiSuccessResponse's body non-null.
 */
class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(
    val body: T
) : ApiResponse<T>()

data class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()

fun <T> Response<T>.handleResult(): ApiResponse<T> {
    return if (isSuccessful) {
        val body = body()
        if (body == null || this.code() == 204) {
            ApiEmptyResponse()
        } else {
            ApiSuccessResponse(
                body = body
            )
        }
    } else {
        val msg = errorBody()?.string()
        val errorMsg = if (msg.isNullOrEmpty()) {
            message()
        } else {
            msg
        }
        ApiErrorResponse(errorMsg ?: "unknown error")
    }
}
