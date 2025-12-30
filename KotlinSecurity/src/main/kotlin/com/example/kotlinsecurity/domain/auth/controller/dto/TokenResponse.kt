package com.example.kotlinsecurity.domain.auth.controller.dto

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)
