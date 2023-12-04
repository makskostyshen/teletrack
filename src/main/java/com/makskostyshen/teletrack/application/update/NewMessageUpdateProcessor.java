package com.makskostyshen.teletrack.application.update;

import com.makskostyshen.teletrack.application.chat.ChatService;
import com.makskostyshen.teletrack.application.message.analyzer.MessageAnalyzer;
import com.makskostyshen.teletrack.application.model.Message;
import com.makskostyshen.teletrack.application.model.update.NewMessageUpdate;
import com.makskostyshen.teletrack.application.telegram.TelegramAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NewMessageUpdateProcessor implements TelegramUpdateProcessor<NewMessageUpdate> {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy");
    private final ChatService chatService;
    private final MessageAnalyzer messageAnalyzer;
    private final TelegramAPI telegramAPI;

    @Value("${app.messages.forward.group.timezone-id:Europe/Kyiv}")
    private String timezoneId;

    @Override
    public void process(final NewMessageUpdate update) {
        Message message = update.getMessage();

        List<Long> targetChatsIds = messageAnalyzer.getTargetChatsIds(message);

        targetChatsIds.forEach(targetChatId ->{
            Message pseudoForwardMessage = createPseudoForwardMessage(update, targetChatId);
            telegramAPI.sendMessage(pseudoForwardMessage);
        });
    }

    private Message createPseudoForwardMessage(final NewMessageUpdate update, final Long targetChatId) {

        String dateTimeValue = LocalDateTime.ofInstant(
                Instant.ofEpochSecond(update.getMessage().getTimeSeconds()),
                ZoneId.of(timezoneId)
        ).format(dateTimeFormatter);

        String sourceChatTitle = chatService.getById(update.getMessage().getChatId()).getTitle();

        return Message.builder()
                .chatId(targetChatId)
                .textContent(
                        String.format("%s\n%s\n\n%s",
                                sourceChatTitle,
                                dateTimeValue,
                                update.getMessage().getTextContent()
                        )
                )
                .build();
    }
}
