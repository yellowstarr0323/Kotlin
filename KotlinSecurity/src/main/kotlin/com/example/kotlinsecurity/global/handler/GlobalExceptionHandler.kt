package com.example.kotlinsecurity.global.handler

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(KotlinSecurityException::class)
    fun handlerException(exception:KotlinSecurityException):ResponseEntity<String>{
        return ResponseEntity.status(exception.errorCode.httpStatus).body(exception.message)
    }
}