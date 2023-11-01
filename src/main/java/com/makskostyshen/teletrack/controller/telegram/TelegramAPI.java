package com.makskostyshen.teletrack.controller.telegram;

import com.makskostyshen.teletrack.dto.AuthenticationCodeDto;
import com.makskostyshen.teletrack.dto.AuthenticationPhoneDto;
import com.makskostyshen.teletrack.config.TDLibParameters;
import com.makskostyshen.teletrack.dto.ForwardMessageDto;

public interface TelegramAPI {
    void sendTDLibParameters(TDLibParameters parameters);
    void sendAuthenticationPhoneNumber(AuthenticationPhoneDto authenticationPhoneDto);
    void sendAuthenticationCode(AuthenticationCodeDto authenticationCodeDto);
    void sendGetAuthorizationStateRequest();
    void sendGetChatsRequest();
    void sendForwardMessageRequest(ForwardMessageDto forwardMessageDto);
}

