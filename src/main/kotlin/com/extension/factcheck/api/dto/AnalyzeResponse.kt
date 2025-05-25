package com.extension.factcheck.api.dto

class AnalyzeResponse(
    val summary: String,
    val credibility: Int,
    val rationale: String,
    val framing: String?,
    val uncertainStatements: List<String>
) {
}