package com.makskostyshen.teletrack.rest.controller;

import com.makskostyshen.teletrack.application.chat.ChatService;
import com.makskostyshen.teletrack.rest.RESTPortMapper;
import com.makskostyshen.teletrack.rest.dto.ChatsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping
    public ResponseEntity<ChatsDto> getInMemoryChats() {
        return ResponseEntity.ok(
                new ChatsDto(
                        chatService.getAll()
                                .stream()
                                .map(RESTPortMapper.I::map)
                                .toList()
                )
        );
    }
}
