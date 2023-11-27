package com.makskostyshen.teletrack.application.update;

import com.makskostyshen.teletrack.application.chat.ChatService;
import com.makskostyshen.teletrack.application.model.update.NewChatUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewChatUpdateProcessor implements TelegramUpdateProcessor<NewChatUpdate> {
    private final ChatService chatService;

    @Override
    public void process(final NewChatUpdate update) {
        chatService.add(update.getChat());
    }
}
