package com.makskostyshen.teletrack.port.rest.controller;

import com.makskostyshen.teletrack.application.telegram.chat.InMemoryChatService;
import com.makskostyshen.teletrack.port.rest.RESTPortMapper;
import com.makskostyshen.teletrack.port.rest.dto.ChatsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatController {
    private final InMemoryChatService inMemoryChatService;

    @GetMapping("/in-memory")
    public ResponseEntity<ChatsDto> getInMemoryChats() {
        return ResponseEntity.ok(
                new ChatsDto(
                        inMemoryChatService.getChats()
                                .stream()
                                .map(RESTPortMapper.I::map)
                                .toList()
                )
        );
    }
}
