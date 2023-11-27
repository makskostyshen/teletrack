package com.makskostyshen.teletrack.application.message.type.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.makskostyshen.teletrack.application.message.analyzer.criterion.Criterion;

public interface MessageTypeCriterionParser {
    Criterion parseCriterion(JsonNode jsonNode);
}
