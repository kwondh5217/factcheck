package com.extension.factcheck.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "factcheck_results")
@Entity
public class FactcheckResult {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "api_usage_log_id", nullable = false)
  private Long apiUsageLogId;

  @Column(name = "model_used", nullable = false, length = 100)
  private String modelUsed;

  @Column(name = "url", columnDefinition = "TEXT", nullable = false)
  private String url;

  @Column(name = "url_hash", length = 64, nullable = false)
  private String urlHash;

  @Column(name = "url_last_modified", nullable = false)
  private LocalDateTime urlLastModified;

  @Column(name = "summary", columnDefinition = "TEXT", nullable = false)
  private String summary;

  @Column(name = "token_usage")
  private Integer tokenUsage;

  @Column(name = "response_time_ms")
  private Integer responseTimeMs;
}
