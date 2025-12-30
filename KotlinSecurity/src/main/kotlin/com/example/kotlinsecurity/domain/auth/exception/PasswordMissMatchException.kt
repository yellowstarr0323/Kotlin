package com.example.kotlinsecurity.domain.auth.exception

import com.example.kotlinsecurity.global.handler.ErrorCode
import com.example.kotlinsecurity.global.handler.KotlinSecurityException

class PasswordMissMatchException : KotlinSecurityException(ErrorCode.PASSWORD_MISMATCH){
}