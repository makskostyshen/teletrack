package com.makskostyshen.teletrack.controller.telegram;

import com.makskostyshen.teletrack.config.TDLibParameters;
import com.makskostyshen.teletrack.dto.ForwardMessageDto;
import com.makskostyshen.teletrack.service.model.AuthorizationState;
import com.makskostyshen.teletrack.service.model.AuthorizationStateUpdate;
import com.makskostyshen.teletrack.service.model.NewMessageUpdate;
import lombok.extern.slf4j.Slf4j;
import org.drinkless.tdlib.TdApi;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Map;

@Slf4j
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TelegramAPIMapper {
    public static TelegramAPIMapper I = Mappers.getMapper(TelegramAPIMapper.class);

    private final Map<Class<?>, AuthorizationState> authorizationStateRegistry =
            Map.of(
                    TdApi.AuthorizationStateWaitTdlibParameters.class, AuthorizationState.WAIT_TDLIB_PARAMETERS,
                    TdApi.AuthorizationStateWaitPhoneNumber.class, AuthorizationState.WAIT_PHONE_NUMBER,
                    TdApi.AuthorizationStateWaitCode.class, AuthorizationState.WAIT_PHONE_CODE,
                    TdApi.AuthorizationStateReady.class, AuthorizationState.READY
            );

    public abstract TdApi.SetTdlibParameters map(TDLibParameters parameters);

    @Mapping(target = "id", source = "message.id")
    @Mapping(target = "chatId", source = "message.chatId")
    @Mapping(target = "threadId", source = "message.messageThreadId")
    @Mapping(target = "textContent", source = "message.content")
    public abstract NewMessageUpdate map(TdApi.UpdateNewMessage message);

    public AuthorizationStateUpdate map(TdApi.UpdateAuthorizationState state) {
        return new AuthorizationStateUpdate(
                authorizationStateRegistry.getOrDefault(
                        state.authorizationState.getClass(),
                        AuthorizationState.UNDEFINED)
        );

    }

    @Mapping(target = "chatId", source = "toChatId")
    @Mapping(target = "messageIds", expression = "java(new long[]{forwardMessageDto.getMessageId()})")
    public abstract TdApi.ForwardMessages map(ForwardMessageDto forwardMessageDto);

    protected String map(final TdApi.MessageContent content) {
        long[] longs = {1L};
        if (content.getClass().equals(TdApi.MessageText.class)) {
            return ((TdApi.MessageText) content).text.text;
        }
        if (content.getClass().equals(TdApi.MessagePhoto.class)) {
            return ((TdApi.MessagePhoto) content).caption.text;
        }
        return "";
    }
}
