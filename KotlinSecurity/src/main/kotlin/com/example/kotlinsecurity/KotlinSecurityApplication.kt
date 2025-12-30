package com.example.kotlinsecurity

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class KotlinSecurityApplication

fun main(args: Array<String>) {
    runApplication<KotlinSecurityApplication>(*args)
}
