package com.example.kotlinsecurity.global.security.jwt

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix ="jwt")
data class JwtProperties(
    val secretKey: String,
    val accessTokenExp: Long,
    val refreshTokenExp: Long
)