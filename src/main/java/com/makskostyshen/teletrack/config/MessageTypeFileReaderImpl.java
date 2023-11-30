package com.makskostyshen.teletrack.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.makskostyshen.teletrack.application.exception.MessageTypeParsingException;
import com.makskostyshen.teletrack.rest.dto.MessageTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MessageTypeFileReaderImpl implements MessageTypeFileReader {
    private final ObjectMapper objectMapper;

    @Override
    public Optional<List<MessageTypeDto>> read(final String filePath) {
        try {
            Path path = Paths.get(filePath);
            String content = new String(Files.readAllBytes(path));
            return Optional.of(
                    objectMapper.readValue(content, new TypeReference<List<MessageTypeDto>>(){})
            );
        } catch (NoSuchFileException | AccessDeniedException e) {
            return Optional.empty();

        } catch (IOException e) {
            throw new MessageTypeParsingException("Failed to load message type configuration file", e);
        }
    }
}
