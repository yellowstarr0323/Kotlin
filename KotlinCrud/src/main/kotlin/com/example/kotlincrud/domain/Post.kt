package com.example.kotlincrud.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Int?=null,

    @Column(nullable = false, columnDefinition = "TEXT", length = 200)
    var title: String,

    @Column(nullable = false, columnDefinition = "TEXT")
    var content: String,

    @Column(nullable = false)
    val createAt: LocalDateTime,

    @Column(nullable = false)
    var updateAt: LocalDateTime,

)
