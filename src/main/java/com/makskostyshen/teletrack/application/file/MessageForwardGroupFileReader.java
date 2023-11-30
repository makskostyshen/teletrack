package com.makskostyshen.teletrack.application.file;

import com.makskostyshen.teletrack.rest.dto.MessageForwardGroupDto;

import java.util.List;
import java.util.Optional;

public interface MessageForwardGroupFileReader {
    Optional<List<MessageForwardGroupDto>> read(String filePath);
}
