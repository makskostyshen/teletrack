package com.makskostyshen.teletrack.rest.controller;

import com.makskostyshen.teletrack.application.message.forward.group.MessageForwardGroupMapper;
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
    public ResponseEntity<Void> postNewMessageForwardGroup(@RequestBody final MessageForwardGroupDto forwardGroupDto) {
        forwardGroupService.add(MessageForwardGroupMapper.I.map(forwardGroupDto));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteMessageForwardGroup(@PathVariable final String name) {
        forwardGroupService.deleteByName(name);
        return ResponseEntity.ok().build();
    }
}
