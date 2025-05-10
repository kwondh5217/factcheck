package com.extension.factcheck.infrastructure;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.util.Collections;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GoogleIdVerifier {

  private final NetHttpTransport transport = new NetHttpTransport();

  private final JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();

  private final GoogleIdTokenVerifier verifier;

  @Value("${chrome.client.id}")
  private String clientId;

  public GoogleIdVerifier() {
    this.verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
        .setAudience(Collections.singletonList(clientId))
        .build();
  }

  @Transactional
  public Payload verifyAndGetPayload(final String idTokenString) {

    Optional<GoogleIdToken> idTokenOptional;
    try {
      idTokenOptional = Optional.ofNullable(verifier.verify(idTokenString));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    GoogleIdToken idToken = idTokenOptional
        .orElseThrow(() -> new RuntimeException("Could not verify Google ID token"));

    return idToken.getPayload();
  }
}
