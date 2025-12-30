package com.example.kotlinsecurity.domain.auth.service

import com.example.kotlinsecurity.domain.auth.controller.dto.ReissueRequest
import com.example.kotlinsecurity.domain.auth.controller.dto.TokenResponse
import com.example.kotlinsecurity.global.security.jwt.JwtTokenProvider
import org.springframework.stereotype.Service

@Service
class ReissueService(
    private val tokenProvider: JwtTokenProvider
) {
    fun reissue(request: ReissueRequest):TokenResponse{

        tokenProvider.validateRefreshToken(request.refreshToken)

        val email = tokenProvider.getEmailByToken(request.refreshToken)

        val accessToken = tokenProvider.generateAccessToken(email)

        val refreshToken = tokenProvider.generateRefreshToken(email)

        return TokenResponse(accessToken, refreshToken)

    }
}