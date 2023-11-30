package com.makskostyshen.teletrack.config;

import com.makskostyshen.teletrack.rest.dto.MessageTypeDto;

import java.util.List;
import java.util.Optional;

public interface MessageTypeFileReader {
    Optional<List<MessageTypeDto>> read(String filePath);
}
