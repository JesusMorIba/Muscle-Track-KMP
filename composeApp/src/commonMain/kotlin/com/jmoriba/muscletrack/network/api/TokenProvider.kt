package com.jmoriba.muscletrack.network.api

class TokenProvider {
    private var token: String? = null

    fun setToken(jwt: String) {
        token = jwt
    }

    fun getToken(): String? = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2NzE4OWRlZi02ZWNlLTQ3OGYtOTdlMi0zZjJhMzdiZmIyMTAiLCJpYXQiOjE3NTU0MzMxNjAsImV4cCI6MTc1NjAzNzk2MH0.6jNbt3LId75nxathJegKgROlxfbVW4MjDNImT-EQkN4"

    fun clearToken() {
        token = null
    }

    fun hasToken(): Boolean = !token.isNullOrEmpty()
}
