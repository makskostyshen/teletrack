package com.makskostyshen.teletrack.service;

import lombok.extern.slf4j.Slf4j;
import org.drinkless.tdlib.Client;

@Slf4j
public class LogMessageHandlerImpl implements Client.LogMessageHandler {

    @Override
    public void onLogMessage(int i, String s) {
        log.info(s);
    }
}
