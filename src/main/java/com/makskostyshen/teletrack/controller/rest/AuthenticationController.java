package com.makskostyshen.teletrack.controller.rest;

import com.makskostyshen.teletrack.controller.telegram.TelegramAPI;
import com.makskostyshen.teletrack.dto.AuthenticationCodeDto;
import com.makskostyshen.teletrack.dto.AuthenticationPhoneDto;
import com.makskostyshen.teletrack.service.model.TelegramApplicationProperties;
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
        telegramAPI.sendAuthenticationPhoneNumber(parameters);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/code")
    public ResponseEntity<Void> sendAuthenticationCode(final @RequestBody AuthenticationCodeDto parameters) {
        telegramAPI.sendAuthenticationCode(parameters);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/state")
    public ResponseEntity<String> getApplicationState() {
        return ResponseEntity.ok(telegramApplicationProperties.getAuthorizationState().name());
    }
}
