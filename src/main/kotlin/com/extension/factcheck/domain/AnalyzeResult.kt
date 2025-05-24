package com.extension.factcheck.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "analyze_results")
@Entity
class AnalyzeResult(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Column(name = "api_usage_log_id", nullable = false)
    var apiUsageLogId: Long = 0L,
    @Column(name = "model_used", nullable = false, length = 100)
    var modelUsed: String,
    @Column(name = "url", columnDefinition = "TEXT", nullable = false)
    var url: String,
    @Column(name = "url_hash", length = 64, nullable = false)
    var urlHash: String,
    @Column(name = "content_hash", length = 64, nullable = false)
    var contentHash: String,
    @Column(name = "url_last_modified", nullable = false)
    var urlLastModified: LocalDateTime,
    @Column(name = "summary", columnDefinition = "TEXT", nullable = false)
    var summary: String,
    @Column(name = "token_usage")
    var tokenUsage: Int? = null,
    @Column(name = "response_time_ms")
    var responseTimeMs: Int? = null,
) : BaseTimeEntity()
