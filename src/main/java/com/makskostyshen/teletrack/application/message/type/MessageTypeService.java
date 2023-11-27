package com.makskostyshen.teletrack.application.message.type;

import com.makskostyshen.teletrack.application.model.MessageType;

import java.util.List;

public interface MessageTypeService {
    List<MessageType> getAll();

    void add(MessageType messageType);

    void removeByName(String name);

    void addAll(List<MessageType> messageTypes);
}
