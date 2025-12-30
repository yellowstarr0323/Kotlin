package com.example.kotlinsecurity.domain.auth.service

import com.example.kotlinsecurity.domain.auth.controller.dto.SignUpRequest
import com.example.kotlinsecurity.domain.auth.exception.EmailAlreadyExistsException
import com.example.kotlinsecurity.domain.user.UserRepository
import com.example.kotlinsecurity.domain.user.model.User
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SignUpService(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository
) {
    @Transactional
    fun signUp(dto: SignUpRequest){
        if(userRepository.existsByEmail(dto.email)){
            throw EmailAlreadyExistsException()
        }

        val encodedPassword = passwordEncoder.encode(dto.password)!!

        val user= User(
            id =null,
            email = dto.email,
            password = encodedPassword
        )

        userRepository.save(user)
    }


}