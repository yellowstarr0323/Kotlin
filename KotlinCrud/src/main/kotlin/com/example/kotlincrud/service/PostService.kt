package com.example.kotlincrud.service

import com.example.kotlincrud.controller.dto.CreatePostRequest
import com.example.kotlincrud.domain.Post
import com.example.kotlincrud.repository.PostRepository
import com.example.kotlincrud.service.exception.PostNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PostService (
    private val postRepository: PostRepository
){
    fun findAll()= postRepository.findAll()

    fun save(dto: CreatePostRequest):Post{
        return postRepository.save(Post(
            title = dto.title,
            content = dto.content,
            createAt = LocalDateTime.now(),
            updateAt = LocalDateTime.now(),
        ))
    }

    fun update(id:Int,dto:CreatePostRequest):Post{
        return postRepository.findById(id).orElseThrow {
            PostNotFoundException()
        }.apply {
            this.title = dto.title
            this.content = dto.content
            this.updateAt = LocalDateTime.now()
        }
            .let { postRepository.save(it) }
    }

    fun delete(id:Int){
        postRepository.deleteById(id)
    }
}