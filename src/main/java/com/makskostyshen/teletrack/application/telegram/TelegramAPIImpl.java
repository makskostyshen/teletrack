package com.makskostyshen.teletrack.application.telegram;

import com.makskostyshen.teletrack.application.model.AuthenticationCode;
import com.makskostyshen.teletrack.application.model.AuthenticationPhone;
import com.makskostyshen.teletrack.application.model.ForwardMessage;
import com.makskostyshen.teletrack.application.model.Message;
import com.makskostyshen.teletrack.config.TDLibParameters;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.drinkless.tdlib.Client;
import org.drinkless.tdlib.TdApi;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class TelegramAPIImpl implements TelegramAPI {
    @Setter
    private Client client;

    @Override
    public void sendTDLibParameters(final TDLibParameters parameters) {
        send(TelegramMapper.I.map(parameters));
    }

    @Override
    public void sendAuthenticationPhoneNumber(final AuthenticationPhone authenticationPhone) {
        send(new TdApi.SetAuthenticationPhoneNumber(authenticationPhone.getPhoneNumber(), null));
    }

    @Override
    public void sendAuthenticationCode(final AuthenticationCode authenticationCode) {
        send(new TdApi.CheckAuthenticationCode(authenticationCode.getCode()));
    }

    @Override
    public void sendGetAuthorizationStateRequest() {
        send(new TdApi.GetAuthorizationState());
    }

    @Override
    public void sendForwardMessageRequest(ForwardMessage forwardMessage) {
        send(TelegramMapper.I.map(forwardMessage));
    }

    @Override
    public void sendLogOutRequest() {
        send(new TdApi.LogOut());
    }

    @Override
    public void sendMessage(Message message) {
        send(TelegramMapper.I.map(message));
    }

    private void send(final TdApi.Function<?> function) {
        client.send(function, null);
    }
}
