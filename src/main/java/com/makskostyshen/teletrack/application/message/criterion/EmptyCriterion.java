package com.makskostyshen.teletrack.application.message.criterion;

import com.makskostyshen.teletrack.application.model.Message;

public class EmptyCriterion implements Criterion {
    @Override
    public boolean isSatisfied(Message message) {
        return true;
    }
}
