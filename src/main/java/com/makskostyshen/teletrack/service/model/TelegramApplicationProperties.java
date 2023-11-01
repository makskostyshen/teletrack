package com.makskostyshen.teletrack.service.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class TelegramApplicationProperties {

    private AuthorizationState authorizationState = AuthorizationState.UNDEFINED;
}


