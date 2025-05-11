package com.extension.factcheck.infrastructure

import com.extension.factcheck.domain.FactcheckResultJpaRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class FactcheckResultRepository(
    private val queryFactory: JPAQueryFactory,
    private val factcheckResultJpaRepository: FactcheckResultJpaRepository,
) : FactcheckResultJpaRepository by factcheckResultJpaRepository
