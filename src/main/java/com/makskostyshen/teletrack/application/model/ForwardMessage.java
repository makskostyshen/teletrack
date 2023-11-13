package com.makskostyshen.teletrack.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForwardMessage {
    private Long toChatId;
    private Long fromChatId;
    private Long messageThreadId;
    private Long messageId;
}
