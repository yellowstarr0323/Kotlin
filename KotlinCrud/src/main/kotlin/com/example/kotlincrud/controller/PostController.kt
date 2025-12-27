package com.example.kotlincrud.controller

import com.example.kotlincrud.controller.dto.CreatePostRequest
import com.example.kotlincrud.domain.Post
import com.example.kotlincrud.service.PostService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController(
    private val postService: PostService
) {

    @PostMapping
    fun createPost(@RequestBody dto: CreatePostRequest):Post{
        return postService.save(dto)
    }

    @GetMapping
    fun getPosts():List<Post>{
        return postService.findAll()
    }

    @PatchMapping("/{id}")
    fun updatePost(@PathVariable id: Int,@RequestBody dto: CreatePostRequest): Post{
        return postService.update(id,dto)
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Int){
        return postService.delete(id)
    }


}