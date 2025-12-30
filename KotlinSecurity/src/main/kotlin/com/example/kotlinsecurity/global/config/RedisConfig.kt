package com.example.kotlinsecurity.global.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
class RedisConfig (
    @Value("\${redis.host}")
    private val host: String,

    @Value("\${redis.port}")
    private val port: Int,

    @Value("\${redis.password}")
    private val password: String
){


    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory{
        val config = RedisStandaloneConfiguration().apply {
            hostName = host
            port= this@RedisConfig.port
            if(this@RedisConfig.password.isNotEmpty()){
                password = RedisPassword.of(this@RedisConfig.password)
            }
        }

        return LettuceConnectionFactory(config)
    }

    @Bean
    fun redisTemplate(
        redisConnectionFactory: RedisConnectionFactory
    ): RedisTemplate<String, String> {
        return RedisTemplate<String, String>().apply {
            connectionFactory = redisConnectionFactory
            keySerializer = StringRedisSerializer()
            valueSerializer = StringRedisSerializer()
            hashKeySerializer = StringRedisSerializer()
            hashValueSerializer = StringRedisSerializer()
        }
    }
}