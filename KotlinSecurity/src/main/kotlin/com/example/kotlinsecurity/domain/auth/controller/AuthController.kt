package com.example.kotlinsecurity.domain.auth.controller

import com.example.kotlinsecurity.domain.auth.controller.dto.ReissueRequest
import com.example.kotlinsecurity.domain.auth.controller.dto.SignInRequest
import com.example.kotlinsecurity.domain.auth.controller.dto.SignUpRequest
import com.example.kotlinsecurity.domain.auth.controller.dto.TokenResponse
import com.example.kotlinsecurity.domain.auth.service.ReissueService
import com.example.kotlinsecurity.domain.auth.service.SignInService
import com.example.kotlinsecurity.domain.auth.service.SignUpService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController (
    private val signUpService: SignUpService,
    private val signInService: SignInService,
    private val reissueService: ReissueService
){

    @PostMapping("/sign-up")
    fun signUp(@RequestBody request: SignUpRequest){
        signUpService.signUp(request)
    }

    @PostMapping("/sign-in")
    fun signIn(@RequestBody request: SignInRequest):TokenResponse {
        return signInService.signIn(request)
    }

    @PostMapping("/reissue")
    fun signIn(@RequestBody request: ReissueRequest): TokenResponse{
        return reissueService.reissue(request)
    }
}