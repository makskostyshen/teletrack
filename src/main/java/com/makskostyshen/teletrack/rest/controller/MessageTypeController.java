package com.makskostyshen.teletrack.rest.controller;

import com.makskostyshen.teletrack.application.message.type.MessageTypeService;
import com.makskostyshen.teletrack.rest.RESTPortMapper;
import com.makskostyshen.teletrack.rest.dto.MessageTypesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message/types")
@RequiredArgsConstructor
public class MessageTypeController {
    private final MessageTypeService messageTypeService;
    @GetMapping
    public ResponseEntity<MessageTypesResponseDto> getAllMessageTypes() {
        return ResponseEntity.ok(
                new MessageTypesResponseDto(
                        messageTypeService.getAll()
                                .stream()
                                .map(RESTPortMapper.I::map)
                                .toList()
                )
        );

    }
}
