package com.makskostyshen.teletrack.application.telegram.update;

import com.makskostyshen.teletrack.application.telegram.chat.InMemoryChatService;
import com.makskostyshen.teletrack.application.model.update.NewChatUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewChatUpdateProcessor implements TelegramUpdateProcessor<NewChatUpdate> {
    private final InMemoryChatService inMemoryChatService;

    @Override
    public void process(final NewChatUpdate update) {
        inMemoryChatService.addChat(update.getChat());
    }
}
