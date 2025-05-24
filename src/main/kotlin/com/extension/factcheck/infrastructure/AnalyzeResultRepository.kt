package com.extension.factcheck.infrastructure

import com.extension.factcheck.domain.AnalyzeResultJpaRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class AnalyzeResultRepository(
    private val queryFactory: JPAQueryFactory,
    private val analyzeResultJpaRepository: AnalyzeResultJpaRepository,
) : AnalyzeResultJpaRepository by analyzeResultJpaRepository
