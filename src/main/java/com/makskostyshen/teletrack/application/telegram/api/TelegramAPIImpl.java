package com.makskostyshen.teletrack.application.telegram.api;

import com.makskostyshen.teletrack.application.model.AuthenticationCode;
import com.makskostyshen.teletrack.application.model.AuthenticationPhone;
import com.makskostyshen.teletrack.application.model.ForwardMessage;
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
        send(TelegramAPIMapper.I.map(parameters));
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
    public void sendGetChatsRequest() {
        client.send(new TdApi.GetChats(new TdApi.ChatListMain(), 10), (var1) -> {
            System.out.println("here");;
        });
        client.send(new TdApi.LoadChats(new TdApi.ChatListMain(), 10), (var1) ->{
            System.out.println("here");
        });
    }

    @Override
    public void sendForwardMessageRequest(ForwardMessage forwardMessage) {
        send(TelegramAPIMapper.I.map(forwardMessage));
    }

    private void send(final TdApi.Function<?> function) {
        client.send(function, null);
    }
}
