package com.makskostyshen.teletrack.application.telegram.handler;

import com.makskostyshen.teletrack.application.telegram.api.TelegramAPIMapper;
import com.makskostyshen.teletrack.application.update.AuthorizationStateUpdateProcessor;
import com.makskostyshen.teletrack.application.update.NewChatUpdateProcessor;
import com.makskostyshen.teletrack.application.update.NewMessageUpdateProcessor;
import lombok.RequiredArgsConstructor;
import org.drinkless.tdlib.Client;
import org.drinkless.tdlib.TdApi;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DelegatingResultHandler implements Client.ResultHandler {
    private final AuthorizationStateUpdateProcessor authorizationStateUpdateProcessor;
    private final NewMessageUpdateProcessor newMessageUpdateProcessor;
    private final NewChatUpdateProcessor newChatUpdateProcessor;

    @Override
    public void onResult(final TdApi.Object update) {

        if (update.getClass().equals(TdApi.UpdateUserStatus.class)) {
            return;
        }
        if (update.getClass().equals(TdApi.UpdateUser.class)) {
            return;
        }
        if (update.getClass().equals(TdApi.UpdateNewChat.class)) {
            newChatUpdateProcessor.process(
                    TelegramAPIMapper.I.map((TdApi.UpdateNewChat) update)
            );
        }
        if (update.getClass().equals(TdApi.UpdateAuthorizationState.class)) {
            authorizationStateUpdateProcessor.process(
                    TelegramAPIMapper.I.map((TdApi.UpdateAuthorizationState) update)
            );
        }
        if (update.getClass().equals(TdApi.UpdateNewMessage.class)) {
            newMessageUpdateProcessor.process(
                    TelegramAPIMapper.I.map((TdApi.UpdateNewMessage) update)
            );
        }
    }
}
