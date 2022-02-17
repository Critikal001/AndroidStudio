package com.example.rentmycar.data.api.client

import com.example.rentmycar.data.api.request.SimpleResponse
import com.example.rentmycar.data.api.request.UserRequest
import retrofit2.Response

abstract class BaseClient {
    // Rewrites response to a safe SimpleResponse used for error handling
    inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }
}