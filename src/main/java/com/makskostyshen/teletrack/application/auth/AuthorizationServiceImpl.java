package com.makskostyshen.teletrack.application.auth;

import com.makskostyshen.teletrack.application.exception.IllegalAuthenticationActionException;
import com.makskostyshen.teletrack.application.model.AuthenticationCode;
import com.makskostyshen.teletrack.application.model.AuthenticationPhone;
import com.makskostyshen.teletrack.application.model.AuthorizationState;
import com.makskostyshen.teletrack.application.telegram.TelegramAPI;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {
    @Getter
    @Setter
    private AuthorizationState authorizationState = AuthorizationState.UNDEFINED;
    private final TelegramAPI telegramAPI;

    @Override
    public void processAuthenticationPhone(AuthenticationPhone authenticationPhone) {
        if (authorizationState != AuthorizationState.WAIT_PHONE_NUMBER) {
            throw new IllegalAuthenticationActionException(
                    "Could not send phone number, because of inappropriate auth state"
            );
        }
        telegramAPI.sendAuthenticationPhoneNumber(authenticationPhone);
    }

    @Override
    public void processAuthenticationCode(AuthenticationCode authenticationCode) {

        if (authorizationState != AuthorizationState.WAIT_PHONE_CODE) {
            throw new IllegalAuthenticationActionException(
                    "Could not send phone code, because of inappropriate auth state");
        }
        telegramAPI.sendAuthenticationCode(authenticationCode);
    }
}
