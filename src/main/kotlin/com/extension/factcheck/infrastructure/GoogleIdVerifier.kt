package com.extension.factcheck.infrastructure

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class GoogleIdVerifier {
    private val transport = NetHttpTransport()
    private val jsonFactory = GsonFactory.getDefaultInstance()

    private val verifier: GoogleIdTokenVerifier = GoogleIdTokenVerifier.Builder(transport, jsonFactory)
        .setAudience(listOf("1056347669242-2agi9u11ic8sjcq299gdch9uadqel7pb.apps.googleusercontent.com"))
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
