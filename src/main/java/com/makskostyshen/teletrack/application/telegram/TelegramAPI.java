package com.makskostyshen.teletrack.application.telegram;

import com.makskostyshen.teletrack.application.model.AuthenticationCode;
import com.makskostyshen.teletrack.application.model.AuthenticationPhone;
import com.makskostyshen.teletrack.application.model.ForwardMessage;
import com.makskostyshen.teletrack.application.model.Message;
import com.makskostyshen.teletrack.config.TDLibParameters;

public interface TelegramAPI {

    void sendTDLibParameters(TDLibParameters parameters);

    void sendAuthenticationPhoneNumber(AuthenticationPhone authenticationPhone);

    void sendAuthenticationCode(AuthenticationCode authenticationCode);

    void sendGetAuthorizationStateRequest();

    void sendForwardMessageRequest(ForwardMessage forwardMessage);

    void sendMessage(Message message);
}

