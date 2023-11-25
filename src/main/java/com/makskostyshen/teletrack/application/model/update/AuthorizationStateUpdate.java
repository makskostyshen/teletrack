package com.makskostyshen.teletrack.application.model.update;

import com.makskostyshen.teletrack.application.model.AuthorizationState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationStateUpdate implements TelegramUpdate {
    private AuthorizationState state;
}
