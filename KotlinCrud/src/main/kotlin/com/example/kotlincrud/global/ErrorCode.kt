package com.example.kotlincrud.global

import org.springframework.http.HttpStatus

enum class ErrorCode (
    val httpStatus: HttpStatus,
    val message: String
){
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "Post Not Found")
}