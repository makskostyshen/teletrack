package com.makskostyshen.teletrack.application.message.type.parser;

import com.makskostyshen.teletrack.application.model.MessageType;

import java.util.List;

public interface MessageTypeParser {
    List<MessageType> parseMultiple(String messageTypesValue);

    MessageType parseSingle(String messageTypeValue);
}
