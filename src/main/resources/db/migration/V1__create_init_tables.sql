CREATE TABLE `users`
(
    `id`                        bigint          NOT NULL AUTO_INCREMENT,
    `email`                     varchar(500)    DEFAULT NULL COMMENT 'Email',
    `date_created`              datetime(6)     NOT NULL COMMENT '등록일시',
    `date_updated`              datetime(6)     NOT NULL COMMENT '수정일시',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT 'User';


CREATE TABLE `factcheck_results`
(
    `id`                     BIGINT       NOT NULL AUTO_INCREMENT,
    `api_usage_log_id`       BIGINT       NOT NULL COMMENT '요청 ID',
    `model_used`             VARCHAR(100) NOT NULL COMMENT '사용한 AI 모델명 (예: gpt-4-turbo)',
    `url`                    TEXT         NOT NULL COMMENT '요청한 문서 URL',
    `url_hash`               VARCHAR(64)  NOT NULL COMMENT '요청 URL의 SHA-256 해시',
    `url_last_modified`      DATETIME(6)  NOT NULL COMMENT '요청한 문서가 마지막으로 수정된 일시',
    `summary`                TEXT         NOT NULL COMMENT '요약 결과',
    `token_usage`            INT          DEFAULT NULL COMMENT 'AI 토큰 사용량',
    `response_time_ms`       INT          DEFAULT NULL COMMENT '응답 시간 (ms)',
    `date_created`           DATETIME(6)  NOT NULL COMMENT '등록일시',
    `date_updated`           DATETIME(6)  NOT NULL COMMENT '수정일시',
    PRIMARY KEY (`id`),
    KEY                     `idx_url_hash` (`url_hash`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT 'FactCheck 결과 캐시';

CREATE TABLE `api_usage_logs`
(
    `id`                    BIGINT       NOT NULL AUTO_INCREMENT,
    `user_id`               BIGINT       NOT NULL COMMENT '요청자 ID',
    `is_cached`             BOOLEAN      NOT NULL COMMENT '캐시된 결과 여부',
    `date_created`          DATETIME(6)  NOT NULL COMMENT '등록일시',
    `date_updated`          DATETIME(6)  NOT NULL COMMENT '수정일시',
    PRIMARY KEY (`id`),
    KEY                     `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT 'API 사용 기록';