package com.makskostyshen.teletrack.application.model.update;

import com.makskostyshen.teletrack.application.model.Chat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewChatUpdate implements TelegramUpdate {
    private Chat chat;
}
