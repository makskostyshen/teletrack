package com.makskostyshen.teletrack.application.telegram.api;

import com.makskostyshen.teletrack.application.model.*;
import com.makskostyshen.teletrack.application.model.update.AuthorizationStateUpdate;
import com.makskostyshen.teletrack.application.model.Message;
import com.makskostyshen.teletrack.application.model.update.NewChatUpdate;
import com.makskostyshen.teletrack.application.model.update.NewMessageUpdate;
import com.makskostyshen.teletrack.config.TDLibParameters;
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

    private final Map<Class<?>, ChatType> chatTypeRegistry =
            Map.of(
                    TdApi.ChatTypePrivate.class, ChatType.PRIVATE,
                    TdApi.ChatTypeSecret.class, ChatType.SECRET,
                    TdApi.ChatTypeSupergroup.class, ChatType.SUPERGROUP,
                    TdApi.ChatTypeBasicGroup.class, ChatType.BASIC_GROUP
            );

    public abstract TdApi.SetTdlibParameters map(TDLibParameters parameters);

    @Mapping(target = "threadId", source = "messageThreadId")
    @Mapping(target = "textContent", source = "message.content")
    public abstract Message map(TdApi.Message message);

    public abstract NewMessageUpdate map(TdApi.UpdateNewMessage updateNewMessage);

    @Mapping(target = "state", source = "authorizationState")
    public abstract AuthorizationStateUpdate map(TdApi.UpdateAuthorizationState state);

    public AuthorizationState map(final TdApi.AuthorizationState authorizationState) {
        return authorizationStateRegistry.getOrDefault(
                authorizationState.getClass(),
                AuthorizationState.UNDEFINED
        );
    }

    public abstract NewChatUpdate map(TdApi.UpdateNewChat updateNewChat);

    public abstract Chat map(TdApi.Chat chat);

    public ChatType map(final TdApi.ChatType chatType) {
        return chatTypeRegistry.getOrDefault(
                chatType.getClass(),
                ChatType.UNDEFINED
        );
    }

    @Mapping(target = "chatId", source = "toChatId")
    @Mapping(target = "messageIds", expression = "java(new long[]{forwardMessage.getMessageId()})")
    public abstract TdApi.ForwardMessages map(ForwardMessage forwardMessage);

    protected String map(final TdApi.MessageContent content) {
        if (content.getClass().equals(TdApi.MessageText.class)) {
            return ((TdApi.MessageText) content).text.text;
        }
        if (content.getClass().equals(TdApi.MessagePhoto.class)) {
            return ((TdApi.MessagePhoto) content).caption.text;
        }
        return "";
    }
}
