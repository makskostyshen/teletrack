package com.makskostyshen.teletrack.application.message.analyzer;

import com.makskostyshen.teletrack.application.model.Message;

import java.util.List;

public interface MessageAnalyzer {
    List<Long> getTargetChatsIds(Message message);
}
