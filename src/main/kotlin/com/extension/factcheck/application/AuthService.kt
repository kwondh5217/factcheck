package com.extension.factcheck.application

import com.extension.factcheck.config.security.TokenProvider
import com.extension.factcheck.domain.User
import com.extension.factcheck.domain.UserMembership
import com.extension.factcheck.domain.UserMembershipGrade
import com.extension.factcheck.domain.UserMembershipJpaRepository
import com.extension.factcheck.infrastructure.GoogleIdVerifier
import com.extension.factcheck.infrastructure.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val userMembershipJpaRepository: UserMembershipJpaRepository,
    private val verifier: GoogleIdVerifier,
    private val tokenProvider: TokenProvider,
) {

    @Transactional
    fun login(idTokenString: String): String {
        val payload = verifier.verifyAndGetPayload(idTokenString)

        val user = userRepository.findByEmail(payload.email)
            ?: userRepository.save(User(email = payload.email))
        val userMembership = (userMembershipJpaRepository.findByUserId(user.id)
            ?: userMembershipJpaRepository.save(UserMembership(userId = user.id)))

        return tokenProvider.create(user.id.toString(), userMembership.grade.toString())
    }

    fun currentUser(): SecurityUser {
        val authentication = SecurityContextHolder.getContext().authentication
        val userId = authentication.principal.toString().toLong()
        val role = authentication.authorities.first().authority

        val grade = when (role.removePrefix("ROLE_")) {
            "FREE" -> UserMembershipGrade.FREE
            "PREMIUM" -> UserMembershipGrade.PREMIUM
            else -> throw IllegalStateException("Unknown role: $role")
        }

        return SecurityUser(userId, grade)
    }
}

data class SecurityUser(
    val userId: Long,
    val role: UserMembershipGrade
)
