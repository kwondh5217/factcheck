package com.extension.factcheck.domain

import org.springframework.data.jpa.repository.JpaRepository

interface FactcheckResultJpaRepository : JpaRepository<FactcheckResult, Long>