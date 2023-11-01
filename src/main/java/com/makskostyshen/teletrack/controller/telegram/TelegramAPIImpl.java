package com.makskostyshen.teletrack.controller.telegram;

import com.makskostyshen.teletrack.config.TDLibParameters;
import com.makskostyshen.teletrack.dto.AuthenticationCodeDto;
import com.makskostyshen.teletrack.dto.AuthenticationPhoneDto;
import com.makskostyshen.teletrack.dto.ForwardMessageDto;
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
    public void sendAuthenticationPhoneNumber(final AuthenticationPhoneDto authenticationPhoneDto) {
        send(new TdApi.SetAuthenticationPhoneNumber(authenticationPhoneDto.getPhoneNumber(), null));
    }

    @Override
    public void sendAuthenticationCode(final AuthenticationCodeDto authenticationCodeDto) {
        send(new TdApi.CheckAuthenticationCode(authenticationCodeDto.getCode()));
    }

    @Override
    public void sendGetAuthorizationStateRequest() {
        send(new TdApi.GetAuthorizationState());
    }

    @Override
    public void sendGetChatsRequest() {
        client.send(new TdApi.GetChats(), null);
    }

    @Override
    public void sendForwardMessageRequest(ForwardMessageDto forwardMessageDto) {
        send(TelegramAPIMapper.I.map(forwardMessageDto));
    }

    private void send(final TdApi.Function<?> function) {
        client.send(function, null);
    }
}
