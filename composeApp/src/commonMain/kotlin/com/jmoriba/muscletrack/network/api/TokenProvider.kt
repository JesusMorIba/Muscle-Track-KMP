package com.jmoriba.muscletrack.network.api

class TokenProvider {
    private var token: String? = null

    fun setToken(jwt: String) {
        token = jwt
    }

    fun getToken(): String? = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2NzE4OWRlZi02ZWNlLTQ3OGYtOTdlMi0zZjJhMzdiZmIyMTAiLCJpYXQiOjE3NTYxMzc0MDksImV4cCI6MTc1Njc0MjIwOX0.TY2VJ7J-_Duj5h85aRDlM3OxOReFIHnTgaU33i8rv9A"

    fun clearToken() {
        token = null
    }

    fun hasToken(): Boolean = !token.isNullOrEmpty()
}
