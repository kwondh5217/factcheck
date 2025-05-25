package com.extension.factcheck.domain

import org.springframework.data.jpa.repository.JpaRepository

interface UserMembershipJpaRepository : JpaRepository<UserMembership, Long> {
    fun findByUserId(userId: Long): UserMembership?
}
