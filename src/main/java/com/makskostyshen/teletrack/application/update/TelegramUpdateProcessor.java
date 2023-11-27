package com.makskostyshen.teletrack.application.update;

import com.makskostyshen.teletrack.application.model.update.TelegramUpdate;

public interface TelegramUpdateProcessor<T extends TelegramUpdate> {
    void process(T update);
}
