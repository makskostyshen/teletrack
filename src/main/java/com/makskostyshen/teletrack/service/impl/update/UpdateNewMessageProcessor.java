package com.makskostyshen.teletrack.service.impl.update;

import com.makskostyshen.teletrack.controller.telegram.TelegramAPI;
import com.makskostyshen.teletrack.dto.ForwardMessageDto;
import com.makskostyshen.teletrack.service.api.update.TelegramUpdateProcessor;
import com.makskostyshen.teletrack.service.model.NewMessageUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateNewMessageProcessor implements TelegramUpdateProcessor<NewMessageUpdate> {
    @Value("${app.telegram.chat.work.sources.ids}")
    private List<Long> workSearchSourceChatsIds;

    @Value("${app.telegram.chat.work.target.id}")
    private Long workSearchTargetChatId;

    private final TelegramAPI telegramAPI;

    @Override
    public void process(NewMessageUpdate update) {
        if (!workSearchSourceChatsIds.contains(update.getChatId())) {
            return;
        }
        String lowercase = update.getTextContent().toLowerCase();
        if (!lowercase.contains("#outstaff") && lowercase.contains("java")) {
            telegramAPI.sendForwardMessageRequest(
                    ForwardMessageDto.builder()
                            .messageId(update.getId())
                            .messageThreadId(update.getThreadId())
                            .fromChatId(update.getChatId())
                            .toChatId(workSearchTargetChatId)
                            .build()
            );
        }
    }
}
