package com.makskostyshen.teletrack.rest.controller;

import com.makskostyshen.teletrack.application.auth.AuthorizationService;
import com.makskostyshen.teletrack.rest.RESTPortMapper;
import com.makskostyshen.teletrack.rest.dto.AuthenticationCodeDto;
import com.makskostyshen.teletrack.rest.dto.AuthenticationPhoneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthorizationService authorizationService;

    @PostMapping("/phone")
    public ResponseEntity<Void> sendAuthorizationPhoneNumber(final @RequestBody AuthenticationPhoneDto parameters) {
        authorizationService.processAuthenticationPhone(RESTPortMapper.I.map(parameters));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/code")
    public ResponseEntity<Void> sendAuthorizationCode(final @RequestBody AuthenticationCodeDto parameters) {
        authorizationService.processAuthenticationCode(RESTPortMapper.I.map(parameters));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/state")
    public ResponseEntity<String> getAuthorizationState() {
        return ResponseEntity.ok(authorizationService.getAuthorizationState().name());
    }
}
