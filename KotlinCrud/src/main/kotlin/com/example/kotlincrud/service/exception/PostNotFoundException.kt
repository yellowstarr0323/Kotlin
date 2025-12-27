package com.example.kotlincrud.service.exception

import com.example.kotlincrud.global.CrudException
import com.example.kotlincrud.global.ErrorCode

class PostNotFoundException: CrudException(
    ErrorCode.POST_NOT_FOUND
)