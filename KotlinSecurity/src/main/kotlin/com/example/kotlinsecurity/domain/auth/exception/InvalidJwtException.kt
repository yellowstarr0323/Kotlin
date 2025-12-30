package com.example.kotlinsecurity.domain.auth.exception

import com.example.kotlinsecurity.global.handler.ErrorCode
import com.example.kotlinsecurity.global.handler.KotlinSecurityException

class InvalidJwtException: KotlinSecurityException(ErrorCode.INVALID_TOKEN)