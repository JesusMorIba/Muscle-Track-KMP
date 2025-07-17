package com.jmoriba.muscletrack.network.api

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class HttpClientProvider(private val tokenProvider: TokenProvider) {

    val publicClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
        defaultRequest {
            contentType(ContentType.Application.Json)
        }
    }

    val privateClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
        defaultRequest {
            contentType(ContentType.Application.Json)
            tokenProvider.getToken()?.let {
                header(HttpHeaders.Authorization, "Bearer $it")
            }
        }
        HttpResponseValidator {
            validateResponse { response ->
                if (response.status == HttpStatusCode.Unauthorized) {
                    throw UnauthorizedException()
                }
            }
        }
    }
}

class UnauthorizedException : Exception("JWT Token is invalid or expired")
