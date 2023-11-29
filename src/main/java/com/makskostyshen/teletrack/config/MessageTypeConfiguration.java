package com.makskostyshen.teletrack.config;

import com.makskostyshen.teletrack.application.exception.MessageTypeParsingException;
import com.makskostyshen.teletrack.application.message.type.MessageTypeService;
import com.makskostyshen.teletrack.application.message.type.parser.MessageTypeParser;
import com.makskostyshen.teletrack.application.model.MessageType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MessageTypeConfiguration {
    private final MessageTypeParser messageTypeParser;
    private final MessageTypeService messageTypeService;

    @Value("${app.message.types.configFile}")
    private String configFilePath;

    @PostConstruct
    public void populateMessageTypes() {
        String content = readFileContent(configFilePath);
        List<MessageType> messageTypes = messageTypeParser.parseMultiple(content);
        messageTypeService.addAll(messageTypes);
    }

    private String readFileContent(final String filePath) {
        try {
            Path path = Paths.get(filePath);
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            throw new MessageTypeParsingException("failed to parse message types file", e);
        }
    }
}
