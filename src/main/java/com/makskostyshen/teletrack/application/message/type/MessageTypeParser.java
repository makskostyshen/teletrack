package com.makskostyshen.teletrack.application.message.type;

import com.makskostyshen.teletrack.application.model.MessageType;

import java.util.List;

public interface MessageTypeParser {
    List<MessageType> parse(String analyzerValue);
}
