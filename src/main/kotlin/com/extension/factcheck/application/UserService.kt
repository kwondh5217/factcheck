package com.extension.factcheck.application

import com.extension.factcheck.domain.User
import com.extension.factcheck.domain.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    @Transactional(readOnly = true)
    fun findById(id: Long): User {
        return userRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("User with ID $id not found")
    }
}
