package com.extension.factcheck.infrastructure

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class GoogleIdVerifier(
    @Value("\${chrome.client.id}")
    private val clientId: String,
) {
    private val transport = NetHttpTransport()
    private val jsonFactory = GsonFactory.getDefaultInstance()

    private val verifier: GoogleIdTokenVerifier = GoogleIdTokenVerifier.Builder(transport, jsonFactory)
        .setAudience(listOf(clientId))
        .build()

    @Transactional
    fun verifyAndGetPayload(idTokenString: String): GoogleIdToken.Payload {
        val idToken = try {
            verifier.verify(idTokenString)
        } catch (e: Exception) {
            throw RuntimeException(e)
        } ?: throw RuntimeException("Could not verify Google ID token")

        return idToken.payload
    }
}
