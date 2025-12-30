package com.example.kotlinsecurity.global.security.jwt

import com.example.kotlinsecurity.domain.auth.exception.InvalidJwtException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.TimeUnit
import javax.crypto.SecretKey

@Component
class JwtTokenProvider (
    private val jwtProperties: JwtProperties,
    private val userDetailsService: UserDetailsService,
    private val redisTemplate: RedisTemplate<String, String>
){
    private lateinit var secretKey: SecretKey
    companion object {
        const val ACCESS_TOKEN_TYPE = "access_token"
        const val REFRESH_TOKEN_TYPE = "refresh_token"
        private const val BEARER_PREFIX = "Bearer "
    }

    @PostConstruct
    fun init(){
        secretKey = Keys.hmacShaKeyFor(
            jwtProperties.secretKey.toByteArray(Charsets.UTF_8)
        )
    }

    fun generateAccessToken(email: String): String{
        return generateToken(email,ACCESS_TOKEN_TYPE,jwtProperties.accessTokenExp)
    }
    fun generateRefreshToken(email: String): String{
        val refreshToken = generateToken(email,REFRESH_TOKEN_TYPE,jwtProperties.refreshTokenExp)
        redisTemplate.opsForValue().set("$REFRESH_TOKEN_TYPE:$email",refreshToken,jwtProperties.refreshTokenExp,TimeUnit.MILLISECONDS)
        return refreshToken
    }

    private fun generateToken(email: String,type:String,time:Long): String{
        val now = Date()
        return Jwts.builder()
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .claim("type",type)
            .setSubject(email)
            .setIssuedAt(now)
            .setExpiration(Date(now.time+time))
            .compact()
    }

    fun validateAccessToken(token: String){
        val claims = parseClaims(token)

        if (claims["type"] != ACCESS_TOKEN_TYPE) {
            throw InvalidJwtException()
        }
    }

    fun validateRefreshToken(token: String) {
        val claims = parseClaims(token)
        val email = claims.subject
        if (claims["type"] != REFRESH_TOKEN_TYPE) {
            throw InvalidJwtException()
        }

        val storedToken = redisTemplate.opsForValue().get("$REFRESH_TOKEN_TYPE:$email")
        if (storedToken != token) {
            throw InvalidJwtException()
        }
    }
    private fun parseClaims(token:String): Claims{
        try {
            return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .body
        }catch(e:ExpiredJwtException){
            throw com.example.kotlinsecurity.domain.auth.exception.ExpiredJwtException()
        }catch(e:JwtException){
            throw InvalidJwtException()
        }
    }

    fun resolveToken(httpServletRequest: HttpServletRequest): String? {
        val token = httpServletRequest.getHeader("Authorization")
        if(token != null && token.startsWith(BEARER_PREFIX)){
            return token.substring(BEARER_PREFIX.length)
        }
        return null
    }

    fun getAuthentication(accessToken: String): UsernamePasswordAuthenticationToken{
        val claims=parseClaims(accessToken)
        val userDetails =userDetailsService.loadUserByUsername(claims.subject)
        return UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
    }

    fun getEmailByToken(token: String): String{
        val claims = parseClaims(token)
        return claims.subject
    }


}