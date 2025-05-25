package com.extension.factcheck.domain

import org.springframework.data.jpa.repository.JpaRepository

interface AnalyzeResultJpaRepository : JpaRepository<AnalyzeResult, Long>
