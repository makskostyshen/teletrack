package com.makskostyshen.teletrack.application.chat;

import com.makskostyshen.teletrack.application.model.Chat;

import java.util.List;

public interface ChatService {

    List<Chat> getAll();

    void add(Chat chat);

    Chat getById(Long id);
}
