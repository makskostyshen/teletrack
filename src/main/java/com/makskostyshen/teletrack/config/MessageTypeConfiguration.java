package com.makskostyshen.teletrack.config;

import com.makskostyshen.teletrack.application.exception.MessageAnalyzerException;
import com.makskostyshen.teletrack.application.message.analyzer.criterion.AndCriterion;
import com.makskostyshen.teletrack.application.message.analyzer.criterion.ContainsTextCriterion;
import com.makskostyshen.teletrack.application.message.analyzer.criterion.NotCriterion;
import com.makskostyshen.teletrack.application.message.analyzer.criterion.OrCriterion;
import com.makskostyshen.teletrack.application.message.type.MessageTypeParser;
import com.makskostyshen.teletrack.application.message.type.MessageTypeService;
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
    private final MessageTypeParser parser;
    private final MessageTypeService messageTypeService;

    @Value("${app.message.types.configFile}")
    private String configFilePath;

    @PostConstruct
    public void populateMessageTypes() {
        String content = readFileContent(configFilePath);

        MessageType messageType = MessageType.builder()
                .name("work-search-message-type")
                .sourceChatsIds(List.of(-1001236684673L,-1001190647252L,-1001189271969L,-1001247711182L,389528139L,592271275L,417467095L))
                .targetChatsIds(List.of(-1002084684686L))
                .criterion(
                        new AndCriterion(
                                List.of(
                                        new ContainsTextCriterion("java"),
                                        new NotCriterion(
                                                new OrCriterion(
                                                        List.of(
                                                                new ContainsTextCriterion("#outstaff"),
                                                                new ContainsTextCriterion("#available")
                                                        )
                                                )
                                        )
                                )
                        )
                )
                .build();

        messageTypeService.add(messageType);
    }

    private String readFileContent(final String filePath) {
        try {
            Path path = Paths.get(filePath);
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            throw new MessageAnalyzerException("failed to parse message analyzer file");
        }
    }
}
