package com.extension.factcheck.application;

import com.extension.factcheck.infrastructure.GoogleIdVerifier;
import com.extension.factcheck.config.security.TokenProvider;
import com.extension.factcheck.domain.User;
import com.extension.factcheck.domain.UserRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

  private final UserRepository userRepository;
  private final GoogleIdVerifier verifier;
  private final TokenProvider tokenProvider;

  @Transactional
  public String login(final String idTokenString) {
    Payload payload = verifier.verifyAndGetPayload(idTokenString);
    Optional<User> userOptional = userRepository.findByEmail(payload.getEmail());

    User user = userOptional.orElseGet(() -> {
      User newUser = User.builder().email(payload.getEmail()).build();
      return userRepository.save(newUser);
    });

    return tokenProvider.create(user.getId().toString());
  }
}