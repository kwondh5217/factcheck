package com.extension.factcheck.application

import com.extension.factcheck.config.security.TokenProvider
import com.extension.factcheck.domain.User
import com.extension.factcheck.infrastructure.GoogleIdVerifier
import com.extension.factcheck.infrastructure.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val verifier: GoogleIdVerifier,
    private val tokenProvider: TokenProvider,
) {

    @Transactional
    fun login(idTokenString: String): String {
        val payload = verifier.verifyAndGetPayload(idTokenString)

        val user = userRepository.findByEmail(payload.email)
            ?: userRepository.save(User(email = payload.email))

        return tokenProvider.create(user.id.toString())
    }
}
