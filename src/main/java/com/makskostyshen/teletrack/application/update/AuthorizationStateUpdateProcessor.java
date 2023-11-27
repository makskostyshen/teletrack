package com.makskostyshen.teletrack.application.update;

import com.makskostyshen.teletrack.application.model.AuthorizationState;
import com.makskostyshen.teletrack.application.model.update.AuthorizationStateUpdate;
import com.makskostyshen.teletrack.config.TDLibParameters;
import com.makskostyshen.teletrack.application.telegram.TelegramAPI;
import com.makskostyshen.teletrack.application.model.TelegramApplicationProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorizationStateUpdateProcessor implements TelegramUpdateProcessor<AuthorizationStateUpdate> {
    private final TelegramApplicationProperties properties;
    private final TelegramAPI telegramAPI;
    private final TDLibParameters tdLibParameters;

    @Override
    public void process(final AuthorizationStateUpdate updateState) {
        if (updateState.getState() == AuthorizationState.WAIT_TDLIB_PARAMETERS) {
            telegramAPI.sendTDLibParameters(tdLibParameters);
        }
        if (updateState.getState() == AuthorizationState.UNDEFINED) {
            log.warn("Authorization state update is not recognized: {}", updateState.getState());
        }
        properties.setAuthorizationState(updateState.getState());
    }
}
