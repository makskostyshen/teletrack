package com.makskostyshen.teletrack.application.message.type;

import com.fasterxml.jackson.databind.JsonNode;
import com.makskostyshen.teletrack.application.exception.MessageTypeParsingException;
import com.makskostyshen.teletrack.application.message.analyzer.criterion.*;
import com.makskostyshen.teletrack.application.model.MessageType;
import com.makskostyshen.teletrack.rest.dto.MessageTypeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public abstract class MessageTypeMapper {
    public static final MessageTypeMapper I = Mappers.getMapper(MessageTypeMapper.class);
    public MessageTypeDto map(final MessageType messageType) {
        return MessageTypeDto.builder()
                .name(messageType.getName())
                .targetChatsIds(messageType.getTargetChatsIds())
                .sourceChatsIds(messageType.getSourceChatsIds())
                .criterion(messageType.getCriterionRepresentation())
                .build();
    }

    public MessageType map(final MessageTypeDto messageTypeDto) {
        return MessageType.builder()
                .name(messageTypeDto.getName())
                .sourceChatsIds(messageTypeDto.getSourceChatsIds())
                .targetChatsIds(messageTypeDto.getTargetChatsIds())
                .criterion(mapCriterion(messageTypeDto.getCriterion()))
                .criterionRepresentation(messageTypeDto.getCriterion())
                .build();
    }

    private Criterion mapCriterion(final JsonNode jsonNode) {
        JsonNode innerNode;
        innerNode = jsonNode.get("and");
        if (innerNode != null) {
            return mapAndCriterion(innerNode);
        }
        innerNode = jsonNode.get("or");
        if (innerNode != null) {
            return mapOrCriterion(innerNode);
        }
        innerNode = jsonNode.get("not");
        if (innerNode != null) {
            return mapNotCriterion(innerNode);
        }
        innerNode = jsonNode.get("containsText");
        if (innerNode != null) {
            return mapContainsTextCriteria(innerNode);
        }
        throw new MessageTypeParsingException(
                String.format("Message type %s cannot be parsed", jsonNode.toPrettyString())
        );
    }

    private Criterion mapContainsTextCriteria(final JsonNode jsonNode) {
        String content = jsonNode.textValue();
        return new ContainsTextCriterion(content);

    }

    private Criterion mapNotCriterion(final JsonNode jsonNode) {
        return new NotCriterion(mapCriterion(jsonNode));
    }

    private Criterion mapOrCriterion(final JsonNode jsonNode) {
        List<Criterion> criteria = new ArrayList<>();
        jsonNode.forEach(innerNode -> criteria.add(mapCriterion(innerNode)));
        return new OrCriterion(criteria);
    }

    private Criterion mapAndCriterion(final JsonNode jsonNode) {
        List<Criterion> criteria = new ArrayList<>();
        jsonNode.forEach(innerNode -> criteria.add(mapCriterion(innerNode)));
        return new AndCriterion(criteria);
    }
}
