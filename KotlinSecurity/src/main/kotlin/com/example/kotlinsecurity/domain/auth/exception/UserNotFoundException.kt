package com.example.kotlinsecurity.domain.auth.exception

import com.example.kotlinsecurity.global.handler.ErrorCode
import com.example.kotlinsecurity.global.handler.KotlinSecurityException

class UserNotFoundException : KotlinSecurityException(ErrorCode.USER_NOT_FOUND)