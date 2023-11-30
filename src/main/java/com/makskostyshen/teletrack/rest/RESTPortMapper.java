package com.makskostyshen.teletrack.rest;

import com.makskostyshen.teletrack.application.message.type.MessageTypeMapper;
import com.makskostyshen.teletrack.application.model.AuthenticationCode;
import com.makskostyshen.teletrack.application.model.AuthenticationPhone;
import com.makskostyshen.teletrack.application.model.Chat;
import com.makskostyshen.teletrack.application.model.MessageType;
import com.makskostyshen.teletrack.rest.dto.AuthenticationCodeDto;
import com.makskostyshen.teletrack.rest.dto.AuthenticationPhoneDto;
import com.makskostyshen.teletrack.rest.dto.ChatDto;
import com.makskostyshen.teletrack.rest.dto.MessageTypeDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RESTPortMapper {
    RESTPortMapper I = Mappers.getMapper(RESTPortMapper.class);

    ChatDto map(Chat chat);

    AuthenticationCode map(AuthenticationCodeDto authenticationCodeDto);

    AuthenticationPhone map(AuthenticationPhoneDto authenticationPhoneDto);

    default MessageTypeDto map(MessageType messageType) {
        return MessageTypeMapper.I.map(messageType);
    }
}
