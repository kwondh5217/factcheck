package com.extension.factcheck.api

import com.extension.factcheck.api.dto.IdTokenRequest
import com.extension.factcheck.application.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/auth")
class AuthController(
    private val authService: AuthService,
) {

    @PostMapping
    fun login(@RequestBody request: IdTokenRequest): ResponseEntity<String> {
        return ResponseEntity.ok().body(authService.login(request.idToken))
    }
}
