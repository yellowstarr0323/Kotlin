package com.example.kotlinsecurity.global.security.auth

import com.example.kotlinsecurity.domain.user.UserRepository
import com.example.kotlinsecurity.domain.user.model.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class CustomUserDetailsService(
    private val userRepository: UserRepository
):UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        val user: User = userRepository.findByEmail(email)?: throw UsernameNotFoundException("User not found")

        return CustomUserDetails(user)
    }
}