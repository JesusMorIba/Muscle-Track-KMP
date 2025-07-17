package com.jmoriba.muscletrack.common.utils

sealed class AppError {
    data class Network(val message: String): AppError()
    data class Http(val code: Int, val message: String): AppError()
    data class Unknown(val message: String): AppError()
    data class Custom(val userMessage: String): AppError()
}