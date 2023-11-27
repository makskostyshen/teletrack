package com.makskostyshen.teletrack.application.message.analyzer.criterion;

import com.makskostyshen.teletrack.application.model.Message;

public interface Criterion {
    boolean isSatisfied(Message message);
}
