package com.jmoriba.muscletrack.di

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import org.koin.dsl.module

fun supabaseModule() = module {
    single {
        createSupabaseClient(
            supabaseUrl = "https://ezssvwzczmcxxbaondrm.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImV6c3N2d3pjem1jeHhiYW9uZHJtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDYwMTI5NDAsImV4cCI6MjA2MTU4ODk0MH0.CQBWblRdBOv6wjvhfiSmTPV4rTl6SYFFlCrlak0f3_w"
        ) {
            install(Postgrest)
            install(Auth)
        }
    }
}