package com.makskostyshen.teletrack.application.model;

import com.makskostyshen.teletrack.application.message.analyzer.criterion.Criterion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageType {
    private String name;
    private List<Long> targetChatsIds;
    private List<Long> sourceChatsIds;
    private Criterion criterion;
}
