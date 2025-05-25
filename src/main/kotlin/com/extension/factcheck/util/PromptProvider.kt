package com.extension.factcheck.util

import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

@Component
class PromptProvider {

    private val factcheckPrompt: String by lazy {
        val resource = ClassPathResource("prompts/factcheck_v1.txt")
        resource.inputStream.bufferedReader(StandardCharsets.UTF_8).use { it.readText() }
    }

    fun getPrompt(title: String, content: String): String {
        return """
            $factcheckPrompt

            제목:
            $title

            내용:
            $content
        """.trimIndent()
    }

}
