package com.makskostyshen.teletrack.application.message.forward.group;

import com.makskostyshen.teletrack.application.model.MessageForwardGroup;

import java.util.List;

public interface MessageForwardGroupService {
    List<MessageForwardGroup> getAll();

    void add(MessageForwardGroup forwardGroup);

    void deleteByName(String name);
}
