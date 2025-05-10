package com.extension.factcheck.api

import com.extension.factcheck.application.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
) {

    @PostMapping
    fun login(@RequestParam googleId: String): ResponseEntity<String> {
        return ResponseEntity.ok().body(authService.login(googleId))
    }
}
