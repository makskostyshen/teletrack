package com.makskostyshen.teletrack.rest.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MessageTypeResponseDto {
    private String name;
    private List<Long> targetChatsIds;
    private List<Long> sourceChatsIds;
    private JsonNode criterion;
}
