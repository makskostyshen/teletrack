package com.makskostyshen.teletrack.application.message.analyzer;

import com.makskostyshen.teletrack.application.message.forward.group.MessageForwardGroupService;
import com.makskostyshen.teletrack.application.model.Message;
import com.makskostyshen.teletrack.application.model.MessageForwardGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageAnalyzerImpl implements MessageAnalyzer {
    private final MessageForwardGroupService forwardGroupService;

    @Override
    public List<Long> getTargetChatsIds(final Message message) {
        return forwardGroupService.getAll().stream()
                .filter(
                        MessageForwardGroup ->
                                MessageForwardGroup.getSourceChatsIds().contains(message.getChatId())
                                        && MessageForwardGroup.getCriterion().isSatisfied(message)
                )
                .map(MessageForwardGroup::getTargetChatsIds)
                .flatMap(Collection::stream)
                .toList();
    }
}
