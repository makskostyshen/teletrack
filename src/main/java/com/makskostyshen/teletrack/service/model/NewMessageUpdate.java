package com.makskostyshen.teletrack.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewMessageUpdate implements TelegramUpdate {
    private Long id;
    private Long chatId;
    private Long threadId;
    private String textContent;
}
