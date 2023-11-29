package com.makskostyshen.teletrack.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MessageTypesResponseDto {
    private List<MessageTypeResponseDto> messageTypes;
}
