package com.makskostyshen.teletrack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForwardMessageDto {
    private Long toChatId;
    private Long fromChatId;
    private Long messageThreadId;
    private Long messageId;
}
