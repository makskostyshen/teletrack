package com.makskostyshen.teletrack.application.chat;

import com.makskostyshen.teletrack.application.model.Chat;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatServiceImpl implements ChatService {
    private final Map<Long, Chat> inMemoryChatRegistry = new HashMap<>();
    @Override
    public List<Chat> getAll() {
        return new ArrayList<>(inMemoryChatRegistry.values());
    }

    @Override
    public void add(final Chat chat) {
        inMemoryChatRegistry.put(chat.getId(), chat);
    }

    @Override
    public Chat getById(final Long id) {
        return inMemoryChatRegistry.get(id);
    }
}
