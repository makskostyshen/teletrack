package com.makskostyshen.teletrack.service.api.update;

import com.makskostyshen.teletrack.service.model.TelegramUpdate;

public interface TelegramUpdateProcessor<T extends TelegramUpdate> {
    void process(T update);
}
