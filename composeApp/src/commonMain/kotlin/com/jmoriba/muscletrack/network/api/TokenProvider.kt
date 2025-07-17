package com.jmoriba.muscletrack.network.api

class TokenProvider {
    private var token: String? = null

    fun setToken(jwt: String) {
        token = jwt
    }

    fun getToken(): String? = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2NzE4OWRlZi02ZWNlLTQ3OGYtOTdlMi0zZjJhMzdiZmIyMTAiLCJpYXQiOjE3NTI2NjU3MDUsImV4cCI6MTc1MzI3MDUwNX0.-Zst8qGviT5bXGrN53QtbRepiKDzGk6jbHJtwqFjoec"

    fun clearToken() {
        token = null
    }

    fun hasToken(): Boolean = !token.isNullOrEmpty()
}
