package com.makskostyshen.teletrack.application.message.type.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.makskostyshen.teletrack.application.exception.MessageTypeParsingException;
import com.makskostyshen.teletrack.application.model.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageTypeParserImpl implements MessageTypeParser {
    private final ObjectMapper mapper;
    private final MessageTypeCriterionParser criterionParser;

    @Override
    public List<MessageType> parseMultiple(final String messageTypesValue) {
        try {
            return mapper.readValue(messageTypesValue, new TypeReference<List<MessageTypeParsingModel>>(){})
                    .stream()
                    .map(this::createMessageType)
                    .toList();

        } catch (JsonProcessingException e) {
            throw new MessageTypeParsingException(
                    String.format("Message types %s could not be parsed", messageTypesValue), e
            );
        }
    }

    @Override
    public MessageType parseSingle(String messageTypeValue) {
        try {
            return createMessageType(
                    mapper.readValue(messageTypeValue, MessageTypeParsingModel.class)
            );

        } catch (JsonProcessingException e) {
            throw new MessageTypeParsingException(
                    String.format("Message type %s could not be parsed", messageTypeValue), e
            );
        }
    }

    private MessageType createMessageType(final MessageTypeParsingModel parsingModel) {
        return MessageType.builder()
                .name(parsingModel.getName())
                .sourceChatsIds(parsingModel.getSourceChatsIds())
                .targetChatsIds(parsingModel.getTargetChatsIds())
                .criterion(criterionParser.parseCriterion(parsingModel.getCriterion()))
                .criterionRepresentation(parsingModel.getCriterion())
                .build();
    }
}
