package com.extension.factcheck.infrastructure

import com.extension.factcheck.api.dto.AnalyzeResponse
import com.extension.factcheck.infrastructure.dto.GroqResponse
import com.extension.factcheck.util.PromptProvider
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class GroqClient(
    private val promptProvider: PromptProvider,
    private val restClient: RestClient,
    private val objectMapper: ObjectMapper,
    @Value("\${groq.api.key}")
    private val key: String,
    @Value("\${groq.api.model}")
    private val model: String,
) {

    companion object {
        private const val GROQ_URL = "https://api.groq.com/openai/v1/chat/completions"
    }

    fun analyzeArticle(title: String, content: String): AnalyzeResponse {
        val prompt = promptProvider.getPrompt(title, content)

        val chatResponse = restClient.post()
            .uri(GROQ_URL)
            .header("Authorization", "Bearer $key")
            .header("Content-Type", "application/json")
            .body(
                mapOf(
                    "model" to model,
                    "messages" to listOf(mapOf("role" to "user", "content" to prompt)),
                    "temperature" to 0.7,
                    "max_tokens" to 1024
                )
            )
            .retrieve()
            .body(GroqResponse::class.java)
            ?: throw RuntimeException("Groq 응답 없음")

        val rawContent = chatResponse.choices.firstOrNull()?.message?.content
            ?: throw RuntimeException("Groq 응답에 content 없음")

        val json = Regex("```(?:json)?\\s*(\\{.*?})\\s*```", RegexOption.DOT_MATCHES_ALL)
            .find(rawContent)
            ?.groupValues?.get(1)
            ?: throw RuntimeException("content 안에 JSON 코드 블록 없음:\n$rawContent")

        return objectMapper.readValue(json, AnalyzeResponse::class.java)
    }
}
