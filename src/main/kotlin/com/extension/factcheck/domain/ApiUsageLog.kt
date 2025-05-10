package com.extension.factcheck.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "api_usage_logs")
@Entity
class ApiUsageLog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Column(name = "user_id", nullable = false)
    var userId: Long = 0L,
    @Column(name = "is_cached", nullable = false)
    var isCached: Boolean,
) : BaseTimeEntity()
