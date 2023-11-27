package com.makskostyshen.teletrack.application.update;

import com.makskostyshen.teletrack.application.message.analyzer.MessageAnalyzer;
import com.makskostyshen.teletrack.application.model.ForwardMessage;
import com.makskostyshen.teletrack.application.model.Message;
import com.makskostyshen.teletrack.application.model.update.NewMessageUpdate;
import com.makskostyshen.teletrack.application.telegram.TelegramAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NewMessageUpdateProcessor implements TelegramUpdateProcessor<NewMessageUpdate> {
    private final MessageAnalyzer messageAnalyzer;
    private final TelegramAPI telegramAPI;

    @Override
    public void process(final NewMessageUpdate update) {
        Message message = update.getMessage();

        List<Long> targetChatsIds = messageAnalyzer.getTargetChatsIds(message);

        targetChatsIds.forEach(targetChatId ->
                        telegramAPI.sendForwardMessageRequest(
                                ForwardMessage.builder()
                                        .messageId(message.getId())
                                        .messageThreadId(message.getThreadId())
                                        .fromChatId(message.getChatId())
                                        .toChatId(targetChatId)
                                        .build()
                        )
                );
    }
}
