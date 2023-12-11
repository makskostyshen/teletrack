package com.makskostyshen.teletrack.application.auth;

import com.makskostyshen.teletrack.application.exception.IllegalAuthorizationActionException;
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
            throw new IllegalAuthorizationActionException(
                    "Could not send phone number: inappropriate auth state"
            );
        }
        telegramAPI.sendAuthenticationPhoneNumber(authenticationPhone);
    }

    @Override
    public void processAuthenticationCode(AuthenticationCode authenticationCode) {
        if (authorizationState != AuthorizationState.WAIT_PHONE_CODE) {
            throw new IllegalAuthorizationActionException(
                    "Could not send phone code: inappropriate auth state");
        }
        telegramAPI.sendAuthenticationCode(authenticationCode);
    }

    @Override
    public void processLogOut() {
        if (authorizationState != AuthorizationState.READY) {
            throw new IllegalAuthorizationActionException(
                    "Could not log out: inappropriate auth state");
        }
        telegramAPI.sendLogOutRequest();
    }
}
