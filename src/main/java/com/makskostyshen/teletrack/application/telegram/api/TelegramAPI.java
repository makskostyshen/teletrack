package com.makskostyshen.teletrack.application.telegram.api;

import com.makskostyshen.teletrack.port.rest.dto.AuthenticationCodeDto;
import com.makskostyshen.teletrack.port.rest.dto.AuthenticationPhoneDto;
import com.makskostyshen.teletrack.config.TDLibParameters;
import com.makskostyshen.teletrack.application.model.ForwardMessage;

public interface TelegramAPI {
    void sendTDLibParameters(TDLibParameters parameters);
    void sendAuthenticationPhoneNumber(AuthenticationPhoneDto authenticationPhoneDto);
    void sendAuthenticationCode(AuthenticationCodeDto authenticationCodeDto);
    void sendGetAuthorizationStateRequest();
    void sendGetChatsRequest();
    void sendForwardMessageRequest(ForwardMessage forwardMessage);
}

