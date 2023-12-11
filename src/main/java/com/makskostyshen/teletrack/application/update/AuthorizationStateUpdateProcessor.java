package com.makskostyshen.teletrack.application.update;

import com.makskostyshen.teletrack.application.auth.AuthorizationService;
import com.makskostyshen.teletrack.application.model.AuthorizationState;
import com.makskostyshen.teletrack.application.model.update.AuthorizationStateUpdate;
import com.makskostyshen.teletrack.application.telegram.TelegramAPI;
import com.makskostyshen.teletrack.config.TDLibParameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorizationStateUpdateProcessor implements TelegramUpdateProcessor<AuthorizationStateUpdate> {
    private final AuthorizationService authorizationService;
    private final TelegramAPI telegramAPI;
    private final TDLibParameters tdLibParameters;

    @Override
    public void process(final AuthorizationStateUpdate updateState) {
        if (updateState.getState() == AuthorizationState.WAIT_TDLIB_PARAMETERS) {
            telegramAPI.sendTDLibParameters(tdLibParameters);
        }
        authorizationService.setAuthorizationState(updateState.getState());
    }
}
