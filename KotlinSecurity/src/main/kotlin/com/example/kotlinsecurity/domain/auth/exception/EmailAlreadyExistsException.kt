package com.example.kotlinsecurity.domain.auth.exception

import com.example.kotlinsecurity.global.handler.ErrorCode
import com.example.kotlinsecurity.global.handler.KotlinSecurityException

class EmailAlreadyExistsException: KotlinSecurityException(ErrorCode.EMAIL_ALREADY_EXISTS) {
}