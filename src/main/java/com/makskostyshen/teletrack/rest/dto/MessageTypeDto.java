package com.makskostyshen.teletrack.rest.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageTypeDto {
    private String name;
    private List<Long> targetChatsIds;
    private List<Long> sourceChatsIds;
    private JsonNode criterion;
}
