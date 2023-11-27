package com.makskostyshen.teletrack.application.message.type;

import com.makskostyshen.teletrack.application.model.MessageType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageTypeServiceImpl implements MessageTypeService {
    private final Map<String, MessageType> inMemoryMessageTypeRegistry = new HashMap<>();

    @Override
    public List<MessageType> getAll() {
        return inMemoryMessageTypeRegistry.values().stream().toList();
    }

    @Override
    public void add(final MessageType messageType) {
        inMemoryMessageTypeRegistry.put(messageType.getName(), messageType);
    }

    @Override
    public void removeByName(final String name) {
        inMemoryMessageTypeRegistry.remove(name);
    }

    @Override
    public void addAll(final List<MessageType> messageTypes) {
        messageTypes.forEach(messageType -> {
            inMemoryMessageTypeRegistry.put(messageType.getName(), messageType);
        });
    }
}
