package com.example.kotlincrud.repository

import com.example.kotlincrud.domain.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Int> {
}