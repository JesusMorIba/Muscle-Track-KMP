package com.jmoriba.muscletrack.common.utils

actual object ErrorHandler {

    actual suspend fun handleException(e: Throwable): AppError {
        val message = e.message ?: "Unexpected error occurred."

        return when {
            message.contains("host", ignoreCase = true) ||
                    message.contains("network", ignoreCase = true) ||
                    message.contains("offline", ignoreCase = true) -> {
                AppError.Network("No internet connection.")
            }

            message.contains("timeout", ignoreCase = true) -> {
                AppError.Network("The request timed out.")
            }

            else -> AppError.Unknown(message)
        }
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
