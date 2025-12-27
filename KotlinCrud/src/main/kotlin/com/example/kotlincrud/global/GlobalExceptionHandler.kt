package com.example.kotlincrud.global

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(CrudException::class)
    fun handleCrudException(e: CrudException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            httpStatus = e.errorCode.httpStatus,
            message = e.errorCode.message,
        )

        return ResponseEntity
            .status(e.errorCode.httpStatus)
            .body(errorResponse)
    }
}