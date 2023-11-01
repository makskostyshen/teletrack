package com.makskostyshen.teletrack.service.model;

public enum AuthorizationState {
    READY,
    UNDEFINED,
    WAIT_TDLIB_PARAMETERS,
    WAIT_PHONE_NUMBER,
    WAIT_PHONE_CODE;
}
