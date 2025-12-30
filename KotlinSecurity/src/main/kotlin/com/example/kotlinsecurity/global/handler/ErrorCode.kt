package com.example.kotlinsecurity.global.handler

import org.springframework.http.HttpStatus

enum class ErrorCode (
    val httpStatus: HttpStatus,
    val message: String
){
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "Expired token."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid token."),
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "Email already exists."),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "Password match."),
}