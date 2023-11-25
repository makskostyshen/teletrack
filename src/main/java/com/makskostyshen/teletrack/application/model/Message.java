package com.makskostyshen.teletrack.application.model;

import com.makskostyshen.teletrack.application.model.update.TelegramUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message implements TelegramUpdate {
    private Long id;
    private Long chatId;
    private Long threadId;
    private String textContent;
}
