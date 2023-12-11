package com.makskostyshen.teletrack.application.auth;

import com.makskostyshen.teletrack.application.model.AuthenticationCode;
import com.makskostyshen.teletrack.application.model.AuthenticationPhone;
import com.makskostyshen.teletrack.application.model.AuthorizationState;

public interface AuthorizationService {

    AuthorizationState getAuthorizationState();

    void processAuthenticationPhone(AuthenticationPhone authenticationPhone);

    void processAuthenticationCode(AuthenticationCode authenticationCode);

    void processLogOut();

    void setAuthorizationState(AuthorizationState authorizationState);
}
