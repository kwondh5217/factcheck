package com.extension.factcheck.config

import org.apache.commons.pool2.impl.GenericObjectPoolConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@Configuration
class RedisConfig(
    private val env: Environment,
    @Value("\${spring.data.redis.host}")
    private var host: String,
    @Value("\${spring.data.redis.port}")
    private var redisPort: String,
    @Value("\${spring.data.redis.lettuce.pool.max-active}")
    private val maxActiveConfig: Int,
    @Value("\${spring.data.redis.lettuce.pool.max-idle}")
    private val maxIdleConfig: Int,
    @Value("\${spring.data.redis.lettuce.pool.min-idle}")
    private val minIdleConfig: Int,
) {

    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory {
        val serverConfig = RedisStandaloneConfiguration(host, redisPort.toInt())

        val poolConfig = GenericObjectPoolConfig<Any>().apply {
            maxTotal = maxActiveConfig
            maxIdle = maxIdleConfig
            minIdle = minIdleConfig
        }

        val clientConfig = LettucePoolingClientConfiguration.builder()
            .commandTimeout(Duration.ofMillis(3))
            .poolConfig(poolConfig)
            .build()

        return LettuceConnectionFactory(serverConfig, clientConfig)
    }

    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, Any> {
        return RedisTemplate<String, Any>().apply {
            this.setConnectionFactory(redisConnectionFactory)
            this.keySerializer = StringRedisSerializer()
            this.valueSerializer = JdkSerializationRedisSerializer()
            this.hashKeySerializer = StringRedisSerializer()
            this.hashValueSerializer = JdkSerializationRedisSerializer()
        }
    }
}
