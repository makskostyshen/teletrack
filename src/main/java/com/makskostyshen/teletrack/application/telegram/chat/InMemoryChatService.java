package com.makskostyshen.teletrack.application.telegram.chat;

import com.makskostyshen.teletrack.application.model.Chat;

import java.util.List;

public interface InMemoryChatService {
    List<Chat> getChats();

    void addChat(Chat chat);
}
