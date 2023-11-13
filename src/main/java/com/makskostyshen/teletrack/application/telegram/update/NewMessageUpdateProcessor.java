package com.makskostyshen.teletrack.application.telegram.update;

import com.makskostyshen.teletrack.application.model.ForwardMessage;
import com.makskostyshen.teletrack.application.model.NewMessageUpdate;
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
        if (!sourceChatsIds.contains(update.getChatId())) {
            return;
        }

        String content = update.getTextContent();
        if (!containAtLeastOne(content, forbiddenKeywords)
                && containAtLeastOne(content, requiredKeywords)) {

            telegramAPI.sendForwardMessageRequest(
                    ForwardMessage.builder()
                            .messageId(update.getId())
                            .messageThreadId(update.getThreadId())
                            .fromChatId(update.getChatId())
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
