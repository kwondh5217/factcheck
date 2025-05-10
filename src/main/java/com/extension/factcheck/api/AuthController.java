package com.extension.factcheck.api;

import com.extension.factcheck.application.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping
  public ResponseEntity<String> login(@RequestParam String googleId) {
    return ResponseEntity.ok().body(authService.login(googleId));
  }

}
