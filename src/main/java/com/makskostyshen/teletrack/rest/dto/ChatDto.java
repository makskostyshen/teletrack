package com.makskostyshen.teletrack.rest.dto;

import com.makskostyshen.teletrack.application.model.ChatType;
import lombok.Data;

@Data
public class ChatDto {
    private Long id;
    private String title;
    private ChatType type;
}
