package com.extension.factcheck.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Slf4j
@Configuration
public class RestClientConfig {

  @Bean
  public RestClient restClient() {
    return RestClient.builder()
        .defaultStatusHandler(
            statusCode -> !statusCode.is2xxSuccessful(),
            (request, response) -> {
              log.error("API 응답 오류: " + response);
            }
        )
        .build();
  }

}
