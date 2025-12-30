package com.example.kotlinsecurity.global.security.auth

import com.example.kotlinsecurity.domain.user.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
    private val user: User
) : UserDetails{
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf(SimpleGrantedAuthority("ROLE_"+user.role))
    }

    override fun getPassword(): String? {
        return user.password
    }

    override fun getUsername(): String {
        return user.email
    }

}