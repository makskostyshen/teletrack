package com.makskostyshen.teletrack.application.update;

import com.makskostyshen.teletrack.application.model.ForwardMessage;
import com.makskostyshen.teletrack.application.model.Message;
import com.makskostyshen.teletrack.application.model.update.NewMessageUpdate;
import com.makskostyshen.teletrack.application.telegram.api.TelegramAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NewMessageUpdateProcessor implements TelegramUpdateProcessor<NewMessageUpdate> {
    @Value("${app.telegram.updates.chats.sources.ids}")
    private List<Long> sourceChatsIds;
    @Value("${app.telegram.updates.chats.target.id}")
    private Long targetChatId;
    @Value("${app.telegram.updates.keywords.require}")
    private List<String> requiredKeywords;
    @Value("${app.telegram.updates.keywords.forbid}")
    private List<String> forbiddenKeywords;

    private final TelegramAPI telegramAPI;

    @Override
    public void process(final NewMessageUpdate update) {
        Message message = update.getMessage();

        if (!sourceChatsIds.contains(update.getMessage().getChatId())) {
            return;
        }

        String content = message.getTextContent();
        if (!containAtLeastOne(content, forbiddenKeywords)
                && containAtLeastOne(content, requiredKeywords)) {

            telegramAPI.sendForwardMessageRequest(
                    ForwardMessage.builder()
                            .messageId(message.getId())
                            .messageThreadId(message.getThreadId())
                            .fromChatId(message.getChatId())
                            .toChatId(targetChatId)
                            .build()
            );
        }
    }

    private boolean containAtLeastOne(final String textContent, final List<String> keywords) {
        String lowercaseTextContent = textContent.toLowerCase();

        List<String> contained = keywords.stream()
                .map(String::toLowerCase)
                .filter(lowercaseTextContent::contains)
                .toList();

        return !contained.isEmpty();
    }
}
