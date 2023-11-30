package com.makskostyshen.teletrack.rest.controller;

import com.makskostyshen.teletrack.application.message.forward.group.MessageForwardGroupService;
import com.makskostyshen.teletrack.rest.RESTPortMapper;
import com.makskostyshen.teletrack.rest.dto.MessageForwardGroupDto;
import com.makskostyshen.teletrack.rest.dto.MessageForwardGroupsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages/forward/groups")
@RequiredArgsConstructor
public class MessageForwardGroupController {
    private final MessageForwardGroupService forwardGroupService;
    @GetMapping
    public ResponseEntity<MessageForwardGroupsDto> getAllMessageForwardGroups() {
        return ResponseEntity.ok(
                new MessageForwardGroupsDto(
                        forwardGroupService.getAll()
                                .stream()
                                .map(RESTPortMapper.I::map)
                                .toList()
                )
        );
    }

    @PostMapping
    public ResponseEntity<MessageForwardGroupDto> postNewMessageForwardGroup(@RequestBody final MessageForwardGroupDto forwardGroupDto) {
        return ResponseEntity.ok(
                RESTPortMapper.I.map(
                        forwardGroupService.add(RESTPortMapper.I.map(forwardGroupDto))
                )
        );
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<MessageForwardGroupDto> deleteMessageForwardGroup(@PathVariable final String name) {
        return ResponseEntity.ok(RESTPortMapper.I.map(forwardGroupService.deleteByName(name)));
    }
}
