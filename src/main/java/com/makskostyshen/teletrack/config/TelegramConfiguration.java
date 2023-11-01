package com.makskostyshen.teletrack.config;

import com.makskostyshen.teletrack.controller.telegram.TelegramAPIImpl;
import com.makskostyshen.teletrack.service.LogMessageHandlerImpl;
import com.makskostyshen.teletrack.service.model.TelegramApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.drinkless.tdlib.Client;
import org.drinkless.tdlib.TdApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TelegramConfiguration {
    private final TelegramAPIImpl telegramAPI;
    private final TDLibParameters applicationProperties;

    @Bean
    Client client(final Client.ResultHandler resultHandler) {
        loadLibraries();
        Client.setLogMessageHandler(0, new LogMessageHandlerImpl());
        Client.execute(new TdApi.SetLogVerbosityLevel(0));
        Client.execute(new TdApi.SetLogStream(new TdApi.LogStreamFile("tdlib.log", 134217728L, false)));

        Client client = Client.create(resultHandler, (Client.ExceptionHandler)null, (Client.ExceptionHandler)null);
        resultHandler.onResult(Client.execute(new TdApi.GetTextEntities("@telegram /test_command https://telegram.org telegram.me @gif @test")));

        telegramAPI.setClient(client);

        return client;
    }

    private void loadLibraries() {
        try {
            String os = System.getProperty("os.name");
            if (os != null && os.toLowerCase().startsWith("windows")) {
                System.loadLibrary("libcrypto-1_1-x64");
                System.loadLibrary("libssl-1_1-x64");
                System.loadLibrary("zlib1");
            }
            System.loadLibrary("tdjni");
        } catch (UnsatisfiedLinkError e) {
            System.out.println("error");
        }
    }
}
