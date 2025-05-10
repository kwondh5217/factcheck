package com.extension.factcheck.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfig {

    private val log: Logger = LoggerFactory.getLogger(javaClass.simpleName)

    @Bean
    fun restClient(): RestClient {
        return RestClient.builder()
            .defaultStatusHandler(
                { statusCode -> !statusCode.is2xxSuccessful },
                { request, response ->
                    log.error("API 응답 오류: {}", response)
                },
            )
            .build()
    }
}
