package com.makskostyshen.teletrack.rest.controller;

import com.makskostyshen.teletrack.application.message.type.MessageTypeMapper;
import com.makskostyshen.teletrack.application.message.type.MessageTypeService;
import com.makskostyshen.teletrack.rest.RESTPortMapper;
import com.makskostyshen.teletrack.rest.dto.MessageTypeDto;
import com.makskostyshen.teletrack.rest.dto.MessageTypesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message/types")
@RequiredArgsConstructor
public class MessageTypeController {
    private final MessageTypeService messageTypeService;
    @GetMapping
    public ResponseEntity<MessageTypesDto> getAllMessageTypes() {
        return ResponseEntity.ok(
                new MessageTypesDto(
                        messageTypeService.getAll()
                                .stream()
                                .map(RESTPortMapper.I::map)
                                .toList()
                )
        );
    }

    @PostMapping
    public ResponseEntity<Void> postNewMessageType(@RequestBody final MessageTypeDto messageTypeDto) {
        messageTypeService.add(MessageTypeMapper.I.map(messageTypeDto));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteMessageType(@PathVariable final String name) {
        messageTypeService.deleteByName(name);
        return ResponseEntity.ok().build();
    }
}
