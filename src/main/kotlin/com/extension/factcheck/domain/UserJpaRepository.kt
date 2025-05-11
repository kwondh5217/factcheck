package com.extension.factcheck.domain

import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : JpaRepository<User?, Long?> {
    fun findByEmail(email: String?): User?
}
