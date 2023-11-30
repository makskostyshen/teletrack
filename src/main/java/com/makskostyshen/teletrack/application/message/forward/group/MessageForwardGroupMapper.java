package com.makskostyshen.teletrack.application.message.forward.group;

import com.fasterxml.jackson.databind.JsonNode;
import com.makskostyshen.teletrack.application.exception.MessageForwardGroupParsingException;
import com.makskostyshen.teletrack.application.message.criterion.*;
import com.makskostyshen.teletrack.application.model.MessageForwardGroup;
import com.makskostyshen.teletrack.rest.dto.MessageForwardGroupDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public abstract class MessageForwardGroupMapper {
    public static final MessageForwardGroupMapper I = Mappers.getMapper(MessageForwardGroupMapper.class);
    public MessageForwardGroupDto map(final MessageForwardGroup forwardGroup) {
        return MessageForwardGroupDto.builder()
                .name(forwardGroup.getName())
                .targetChatsIds(forwardGroup.getTargetChatsIds())
                .sourceChatsIds(forwardGroup.getSourceChatsIds())
                .criterion(forwardGroup.getCriterionRepresentation())
                .build();
    }

    public MessageForwardGroup map(final MessageForwardGroupDto forwardGroupDto) {
        return MessageForwardGroup.builder()
                .name(forwardGroupDto.getName())
                .sourceChatsIds(forwardGroupDto.getSourceChatsIds())
                .targetChatsIds(forwardGroupDto.getTargetChatsIds())
                .criterion(mapCriterion(forwardGroupDto.getCriterion()))
                .criterionRepresentation(forwardGroupDto.getCriterion())
                .build();
    }

    private Criterion mapCriterion(final JsonNode jsonNode) {
        return jsonNode != null
                ? mapNodeCriterion(jsonNode)
                : new EmptyCriterion();
    }

    private Criterion mapNodeCriterion(final JsonNode jsonNode) {
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
        throw new MessageForwardGroupParsingException(
                String.format("Message type %s cannot be parsed", jsonNode.toPrettyString())
        );
    }

    private Criterion mapContainsTextCriteria(final JsonNode jsonNode) {
        String content = jsonNode.textValue();
        return new ContainsTextCriterion(content);

    }

    private Criterion mapNotCriterion(final JsonNode jsonNode) {
        return new NotCriterion(mapNodeCriterion(jsonNode));
    }

    private Criterion mapOrCriterion(final JsonNode jsonNode) {
        List<Criterion> criteria = new ArrayList<>();
        jsonNode.forEach(innerNode -> criteria.add(mapNodeCriterion(innerNode)));
        return new OrCriterion(criteria);
    }

    private Criterion mapAndCriterion(final JsonNode jsonNode) {
        List<Criterion> criteria = new ArrayList<>();
        jsonNode.forEach(innerNode -> criteria.add(mapNodeCriterion(innerNode)));
        return new AndCriterion(criteria);
    }
}
