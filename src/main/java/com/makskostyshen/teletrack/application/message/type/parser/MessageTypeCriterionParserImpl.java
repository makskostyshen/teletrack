package com.makskostyshen.teletrack.application.message.type.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.makskostyshen.teletrack.application.exception.MessageTypeParsingException;
import com.makskostyshen.teletrack.application.message.analyzer.criterion.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageTypeCriterionParserImpl implements MessageTypeCriterionParser {
    @Override
    public Criterion parseCriterion(final JsonNode jsonNode) {
        JsonNode innerNode;
        innerNode = jsonNode.get("and");
        if (innerNode != null) {
            return parseAndCriterion(innerNode);
        }
        innerNode = jsonNode.get("or");
        if (innerNode != null) {
            return parseOrCriterion(innerNode);
        }
        innerNode = jsonNode.get("not");
        if (innerNode != null) {
            return parseNotCriterion(innerNode);
        }
        innerNode = jsonNode.get("containsText");
        if (innerNode != null) {
            return parseContainsTextCriteria(innerNode);
        }
        throw new MessageTypeParsingException(
                String.format("Message type %s cannot be parsed", jsonNode.toPrettyString())
        );
    }

    private Criterion parseContainsTextCriteria(final JsonNode jsonNode) {
        String content = jsonNode.textValue();
        return new ContainsTextCriterion(content);

    }

    private Criterion parseNotCriterion(final JsonNode jsonNode) {
        return new NotCriterion(parseCriterion(jsonNode));
    }

    private Criterion parseOrCriterion(final JsonNode jsonNode) {
        List<Criterion> criteria = new ArrayList<>();
        jsonNode.forEach(innerNode -> criteria.add(parseCriterion(innerNode)));
        return new OrCriterion(criteria);
    }

    private Criterion parseAndCriterion(final JsonNode jsonNode) {
        List<Criterion> criteria = new ArrayList<>();
        jsonNode.forEach(innerNode -> criteria.add(parseCriterion(innerNode)));
        return new AndCriterion(criteria);
    }
}
