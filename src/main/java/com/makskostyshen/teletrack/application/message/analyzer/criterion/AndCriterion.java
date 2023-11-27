package com.makskostyshen.teletrack.application.message.analyzer.criterion;

import com.makskostyshen.teletrack.application.model.Message;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AndCriterion implements Criterion {
    private List<Criterion> nestedCriteria;

    @Override
    public boolean isSatisfied(final Message message) {
        for (Criterion criteria: nestedCriteria) {
            if (!criteria.isSatisfied(message)) {
                return false;
            }
        }
        return true;
    }
}
