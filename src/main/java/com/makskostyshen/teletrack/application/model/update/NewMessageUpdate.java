package com.makskostyshen.teletrack.application.model.update;

import com.makskostyshen.teletrack.application.model.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewMessageUpdate implements TelegramUpdate {
    private Message message;
}
