package com.makskostyshen.teletrack.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MessageTypesDto {
    private List<MessageTypeDto> messageTypes;
}
