package com.makskostyshen.teletrack.port.rest;

import com.makskostyshen.teletrack.application.model.AuthenticationCode;
import com.makskostyshen.teletrack.application.model.AuthenticationPhone;
import com.makskostyshen.teletrack.application.model.Chat;
import com.makskostyshen.teletrack.port.rest.dto.AuthenticationCodeDto;
import com.makskostyshen.teletrack.port.rest.dto.AuthenticationPhoneDto;
import com.makskostyshen.teletrack.port.rest.dto.ChatDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RESTPortMapper {
    RESTPortMapper I = Mappers.getMapper(RESTPortMapper.class);

    ChatDto map(Chat chat);

    AuthenticationCode map(AuthenticationCodeDto authenticationCodeDto);

    AuthenticationPhone map(AuthenticationPhoneDto authenticationPhoneDto);
}
