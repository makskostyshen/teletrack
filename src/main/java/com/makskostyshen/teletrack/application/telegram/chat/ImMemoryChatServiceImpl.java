package com.makskostyshen.teletrack.application.telegram.chat;

import com.makskostyshen.teletrack.application.model.Chat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ImMemoryChatServiceImpl implements InMemoryChatService {
    private final Set<Chat> inMemoryChatStorage = new HashSet<>();
    @Override
    public List<Chat> getChats() {
        return new ArrayList<>(inMemoryChatStorage);
    }

    @Override
    public void addChat(final Chat chat) {
        inMemoryChatStorage.add(chat);
    }
}
