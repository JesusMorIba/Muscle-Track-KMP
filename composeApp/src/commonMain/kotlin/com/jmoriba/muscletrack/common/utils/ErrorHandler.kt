package com.jmoriba.muscletrack.common.utils

expect object ErrorHandler {
    suspend fun handleException(e: Throwable): AppError
    fun getUserFriendlyMessage(error: AppError): String
}