package com.extension.factcheck.infrastructure

import com.extension.factcheck.domain.UserJpaRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    private val queryFactory: JPAQueryFactory,
    private val userJpaRepository: UserJpaRepository,
) : UserJpaRepository by userJpaRepository
