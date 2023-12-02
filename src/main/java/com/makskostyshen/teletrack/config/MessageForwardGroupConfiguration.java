package com.makskostyshen.teletrack.config;

import com.makskostyshen.teletrack.application.file.MessageForwardGroupFileReader;
import com.makskostyshen.teletrack.application.message.forward.group.MessageForwardGroupMapper;
import com.makskostyshen.teletrack.application.message.forward.group.MessageForwardGroupService;
import com.makskostyshen.teletrack.rest.dto.MessageForwardGroupDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MessageForwardGroupConfiguration {
    private final MessageForwardGroupFileReader forwardGroupFileReader;
    private final MessageForwardGroupService forwardGroupService;

    @Value("${app.messages.forward.group.config-file}")
    private String configFilePath;

    @PostConstruct
    public void populateMessageForwardGroups() {
        Optional<List<MessageForwardGroupDto>> dtosOptional = forwardGroupFileReader.read(configFilePath);

        dtosOptional.ifPresentOrElse(
                dtos -> {
                    dtos.forEach(dto -> forwardGroupService.add(MessageForwardGroupMapper.I.map(dto)));
                    log.info("Message type configuration file is loaded");
                },
                () -> log.info("Message type configuration file is not loaded")
        );
    }
}
