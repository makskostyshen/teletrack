package com.makskostyshen.teletrack.rest.controller;

import com.makskostyshen.teletrack.application.telegram.TelegramAPI;
import com.makskostyshen.teletrack.rest.RESTPortMapper;
import com.makskostyshen.teletrack.rest.dto.AuthenticationCodeDto;
import com.makskostyshen.teletrack.rest.dto.AuthenticationPhoneDto;
import com.makskostyshen.teletrack.application.model.TelegramApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final TelegramAPI telegramAPI;
    private final TelegramApplicationProperties telegramApplicationProperties;

    @PostMapping("/phone")
    public ResponseEntity<Void> sendAuthenticationPhoneNumber(final @RequestBody AuthenticationPhoneDto parameters) {
        telegramAPI.sendAuthenticationPhoneNumber(RESTPortMapper.I.map(parameters));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/code")
    public ResponseEntity<Void> sendAuthenticationCode(final @RequestBody AuthenticationCodeDto parameters) {
        telegramAPI.sendAuthenticationCode(RESTPortMapper.I.map(parameters));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/state")
    public ResponseEntity<String> getApplicationState() {
        return ResponseEntity.ok(telegramApplicationProperties.getAuthorizationState().name());
    }
}
