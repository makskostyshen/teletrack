package com.makskostyshen.teletrack.application.chat;

import com.makskostyshen.teletrack.application.model.Chat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ChatServiceImpl implements ChatService {
    private final Set<Chat> inMemoryChatRegistry = new HashSet<>();
    @Override
    public List<Chat> getAll() {
        return new ArrayList<>(inMemoryChatRegistry);
    }

    @Override
    public void add(final Chat chat) {
        inMemoryChatRegistry.add(chat);
    }
}
