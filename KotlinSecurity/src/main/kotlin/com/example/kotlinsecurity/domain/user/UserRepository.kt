package com.example.kotlinsecurity.domain.user

import com.example.kotlinsecurity.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>{
    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
}