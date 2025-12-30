package com.example.kotlinsecurity.global.handler

abstract class KotlinSecurityException(val errorCode: ErrorCode):RuntimeException(errorCode.message)