package com.example.kotlincrud.global

abstract class CrudException(val errorCode: ErrorCode) : RuntimeException() {

}