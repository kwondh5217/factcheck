package com.extension.factcheck.infrastructure

import com.extension.factcheck.domain.ApiUsageLogJpaRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class ApiUsageLogRepository(
    private val queryFactory: JPAQueryFactory,
    private val apiUsageLogJpaRepository: ApiUsageLogJpaRepository,
) : ApiUsageLogJpaRepository by apiUsageLogJpaRepository
