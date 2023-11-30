package com.makskostyshen.teletrack.config;

import com.makskostyshen.teletrack.application.message.type.MessageTypeMapper;
import com.makskostyshen.teletrack.application.message.type.MessageTypeService;
import com.makskostyshen.teletrack.rest.dto.MessageTypeDto;
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
public class MessageTypeConfiguration {
    private final MessageTypeFileReader messageTypeFileReader;
    private final MessageTypeService messageTypeService;

    @Value("${app.message.types.configFile}")
    private String configFilePath;

    @PostConstruct
    public void populateMessageTypes() {
        Optional<List<MessageTypeDto>> dtosOptional = messageTypeFileReader.read(configFilePath);

        dtosOptional.ifPresentOrElse(
                dtos -> {
                    dtos.forEach(dto -> messageTypeService.add(MessageTypeMapper.I.map(dto)));
                    log.info("Message type configuration file is loaded");
                },
                () -> log.info("Message type configuration file is not loaded")
        );
    }
}
