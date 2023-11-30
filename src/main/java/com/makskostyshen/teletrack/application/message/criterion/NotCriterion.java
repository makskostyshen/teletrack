package com.makskostyshen.teletrack.application.message.criterion;

import com.makskostyshen.teletrack.application.model.Message;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotCriterion implements Criterion {
    private Criterion nestedCriterion;
    @Override
    public boolean isSatisfied(final Message message) {
        return !nestedCriterion.isSatisfied(message);
    }
}
