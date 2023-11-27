package com.makskostyshen.teletrack.application.message.type.parser;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.List;

@Data
public class MessageTypeParsingModel {
    private String name;
    private List<Long> targetChatsIds;
    private List<Long> sourceChatsIds;
    private JsonNode criterion;
}
