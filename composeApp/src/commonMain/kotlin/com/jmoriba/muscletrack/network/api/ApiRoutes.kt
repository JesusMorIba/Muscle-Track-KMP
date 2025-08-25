package com.jmoriba.muscletrack.network.api

object ApiRoutes {
    const val BASE_URL = "http://192.168.3.1:3000/api"

    object Auth {
        const val Login = "/auth/login"
        const val Register = "/auth/register"
        const val RefreshToken = "/auth/refresh"
        const val Logout = "/auth/logout"
    }

    object Dashboard {
        const val Stats = "/dashboard"
    }

    object User {
        const val Profile = "/user/profile"
        const val UpdateProfile = "/user/update"
    }

    object Workouts {
        const val All = "/workouts"
        fun byId(id: String) = "/workouts/$id"
        const val Create = "/workouts/create"
    }

    object Exercises {
        const val All = "/exercises"
        fun detail(id: String) = "/exercises/$id"
    }

    object Reports {
        const val All = "/reports"
        fun detail(id: String) = "/reports/$id"
    }
}