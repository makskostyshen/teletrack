package com.makskostyshen.teletrack.port.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChatsDto {
    private List<ChatDto> chats;
}
