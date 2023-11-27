package com.makskostyshen.teletrack.application.message.analyzer;

import com.makskostyshen.teletrack.application.message.type.MessageTypeService;
import com.makskostyshen.teletrack.application.model.Message;
import com.makskostyshen.teletrack.application.model.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageAnalyzerImpl implements MessageAnalyzer {
    private final MessageTypeService messageTypeService;

    @Override
    public List<Long> getTargetChatsIds(final Message message) {
        return messageTypeService.getAll().stream()
                .filter(
                        messageType ->
                                messageType.getSourceChatsIds().contains(message.getChatId())
                                        && messageType.getCriterion().isSatisfied(message)
                )
                .map(MessageType::getTargetChatsIds)
                .flatMap(Collection::stream)
                .toList();
    }
}
