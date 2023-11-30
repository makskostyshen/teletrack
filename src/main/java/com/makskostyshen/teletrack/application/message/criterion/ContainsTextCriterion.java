package com.makskostyshen.teletrack.application.message.criterion;

import com.makskostyshen.teletrack.application.model.Message;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ContainsTextCriterion implements Criterion {
    private String text;
    @Override
    public boolean isSatisfied(final Message message) {
        String lowercaseMessageContent = message.getTextContent().toLowerCase();

        return lowercaseMessageContent.contains(text.toLowerCase());
    }
}
