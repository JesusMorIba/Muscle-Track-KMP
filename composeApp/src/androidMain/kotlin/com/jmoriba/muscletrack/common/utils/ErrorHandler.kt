package com.jmoriba.muscletrack.common.utils

import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.net.UnknownHostException
import java.net.SocketTimeoutException

actual object ErrorHandler {

    actual suspend fun handleException(e: Throwable): AppError {
        return when (e) {
            is UnknownHostException -> AppError.Network("No internet connection.")
            is SocketTimeoutException -> AppError.Network("Request timed out.")
            is ResponseException -> parseKtorHttpError(e)
            else -> AppError.Unknown(e.message ?: "Unexpected error occurred.")
        }
    }

    private suspend fun parseKtorHttpError(e: ResponseException): AppError {
        val code = e.response.status.value
        val message = try {
            val bodyText = e.response.bodyAsText()
            val json = Json.parseToJsonElement(bodyText).jsonObject
            json["message"]?.jsonPrimitive?.content ?: "Server error"
        } catch (ex: Exception) {
            "Server error"
        }

        return AppError.Http(code, message)
    }

    actual fun getUserFriendlyMessage(error: AppError): String {
        return when (error) {
            is AppError.Network -> error.message
            is AppError.Http -> when (error.code) {
                401 -> "Unauthorized. Please log in."
                403 -> "Permission denied."
                404 -> "Resource not found."
                500 -> "Server error. Try again later."
                else -> error.message
            }
            is AppError.Unknown -> error.message
            is AppError.Custom -> error.userMessage
        }
    }
}

