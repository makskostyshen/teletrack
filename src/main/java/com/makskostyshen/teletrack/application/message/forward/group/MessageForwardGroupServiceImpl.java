package com.makskostyshen.teletrack.application.message.forward.group;

import com.makskostyshen.teletrack.application.exception.MessageForwardGroupAlreadyExistsException;
import com.makskostyshen.teletrack.application.exception.MessageForwardGroupNotFoundException;
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
    public MessageForwardGroup add(final MessageForwardGroup forwardGroup) {
        if (!inMemoryMessageForwardGroupRegistry.containsKey(forwardGroup.getName())) {
            inMemoryMessageForwardGroupRegistry.put(forwardGroup.getName(), forwardGroup);
            return forwardGroup;
        }
        throw new MessageForwardGroupAlreadyExistsException(
                String.format("Could now add message forward group %s: it already exists", forwardGroup.getName()));
    }

    @Override
    public MessageForwardGroup deleteByName(final String name) {
        if (inMemoryMessageForwardGroupRegistry.containsKey(name)) {
            return inMemoryMessageForwardGroupRegistry.remove(name);
        }
        throw new MessageForwardGroupNotFoundException(
                String.format("Could now delete message forward group %s: it is not found", name));
    }
}
