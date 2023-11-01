package com.makskostyshen.teletrack.service.impl.update;

import com.makskostyshen.teletrack.config.TDLibParameters;
import com.makskostyshen.teletrack.controller.telegram.TelegramAPI;
import com.makskostyshen.teletrack.service.api.update.TelegramUpdateProcessor;
import com.makskostyshen.teletrack.service.model.AuthorizationState;
import com.makskostyshen.teletrack.service.model.AuthorizationStateUpdate;
import com.makskostyshen.teletrack.service.model.TelegramApplicationProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateAuthorizationStateProcessor implements TelegramUpdateProcessor<AuthorizationStateUpdate> {
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
