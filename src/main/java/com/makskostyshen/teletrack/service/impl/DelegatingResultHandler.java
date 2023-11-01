package com.makskostyshen.teletrack.service.impl;

import com.makskostyshen.teletrack.controller.telegram.TelegramAPIMapper;
import com.makskostyshen.teletrack.service.impl.update.UpdateAuthorizationStateProcessor;
import com.makskostyshen.teletrack.service.impl.update.UpdateNewMessageProcessor;
import lombok.RequiredArgsConstructor;
import org.drinkless.tdlib.Client;
import org.drinkless.tdlib.TdApi;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DelegatingResultHandler implements Client.ResultHandler {
    private final UpdateAuthorizationStateProcessor updateAuthorizationStateProcessor;
    private final UpdateNewMessageProcessor updateNewMessageProcessor;

    @Override
    public void onResult(TdApi.Object update) {

        if (update.getClass().equals(TdApi.UpdateUserStatus.class)) {
            return;
        }
        if (update.getClass().equals(TdApi.UpdateUser.class)) {
            return;
        }
        if (update.getClass().equals(TdApi.UpdateAuthorizationState.class)) {
            updateAuthorizationStateProcessor.process(
                    TelegramAPIMapper.I.map((TdApi.UpdateAuthorizationState) update)
            );
        }
        if (update.getClass().equals(TdApi.UpdateNewMessage.class)) {
            updateNewMessageProcessor.process(
                    TelegramAPIMapper.I.map((TdApi.UpdateNewMessage) update)
            );
        }
    }
}
