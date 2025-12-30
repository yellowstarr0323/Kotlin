package com.example.kotlinsecurity.domain.auth.service

import com.example.kotlinsecurity.domain.auth.controller.dto.SignInRequest
import com.example.kotlinsecurity.domain.auth.controller.dto.TokenResponse
import com.example.kotlinsecurity.domain.auth.exception.PasswordMissMatchException
import com.example.kotlinsecurity.domain.auth.exception.UserNotFoundException
import com.example.kotlinsecurity.domain.user.UserRepository
import com.example.kotlinsecurity.domain.user.model.User
import com.example.kotlinsecurity.global.security.jwt.JwtTokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SignInService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: JwtTokenProvider
) {

    fun signIn(request: SignInRequest): TokenResponse {
        val user: User = userRepository.findByEmail(request.email)
            ?: throw UserNotFoundException()

        if(!passwordEncoder.matches(request.password, user.password)) {
           throw PasswordMissMatchException()
        }

        val accessToken = tokenProvider.generateAccessToken(user.email)
        val refreshToken = tokenProvider.generateRefreshToken(user.email)

        return TokenResponse(accessToken, refreshToken)

    }
}