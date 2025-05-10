package com.extension.factcheck.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

  private static final Long MAX_AGE_SECS = 3600L;

  @Value("${chrome.client.id}")
  private String clientId;

  @Override
  public void addCorsMappings(final CorsRegistry registry) {
    registry.addMapping("/**") // 모든 경로에 대하여
        .allowedOrigins(
            "chrome-extension://" + clientId
        )
        .allowedMethods(
            "GET",
            "POST",
            "PUT",
            "PATCH",
            "DELETE",
            "OPTIONS"
        )
        .allowedHeaders("*")
        .allowCredentials(true)
        .maxAge(MAX_AGE_SECS);
  }
}
