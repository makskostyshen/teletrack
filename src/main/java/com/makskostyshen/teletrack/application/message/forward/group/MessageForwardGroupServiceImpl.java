package com.makskostyshen.teletrack.application.message.forward.group;

import com.makskostyshen.teletrack.application.model.MessageForwardGroup;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageForwardGroupServiceImpl implements MessageForwardGroupService {
    private final Map<String, MessageForwardGroup> inMemoryMessageForwardGroupRegistry = new HashMap<>();

    @Override
    public List<MessageForwardGroup> getAll() {
        return inMemoryMessageForwardGroupRegistry.values().stream().toList();
    }

    @Override
    public void add(final MessageForwardGroup forwardGroup) {
        inMemoryMessageForwardGroupRegistry.put(forwardGroup.getName(), forwardGroup);
    }

    @Override
    public void deleteByName(final String name) {
        inMemoryMessageForwardGroupRegistry.remove(name);
    }
}
