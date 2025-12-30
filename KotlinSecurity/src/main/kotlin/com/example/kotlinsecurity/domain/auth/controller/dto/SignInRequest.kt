package com.example.kotlinsecurity.domain.auth.controller.dto

data class SignInRequest(
    val email: String,
    val password: String
)
