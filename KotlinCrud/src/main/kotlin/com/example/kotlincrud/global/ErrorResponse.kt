package com.example.kotlincrud.global

import org.springframework.http.HttpStatus

data class ErrorResponse(
    val httpStatus: HttpStatus,
    val message: String,
)
