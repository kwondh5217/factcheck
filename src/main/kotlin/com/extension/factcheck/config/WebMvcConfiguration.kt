package com.extension.factcheck.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfiguration(
    @Value("\${chrome.client.id}")
    private val clientId: String? = null,
) : WebMvcConfigurer {

    private val MAX_AGE_SECS: Long = 3600

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**") // 모든 경로에 대하여
            .allowedOrigins(
                "chrome-extension://nhggehajihflgpgghbipiikajjigaikg",
                "chrome-extension://fpemdhnohkbigbldpphedibmkmdpoilg"
            )
            .allowedMethods(
                "GET",
                "POST",
                "PUT",
                "PATCH",
                "DELETE",
                "OPTIONS",
            )
            .allowedHeaders("*")
            .allowCredentials(true)
            .maxAge(MAX_AGE_SECS)
    }
}
