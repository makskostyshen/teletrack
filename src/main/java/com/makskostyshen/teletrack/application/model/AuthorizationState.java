package com.makskostyshen.teletrack.application.model;

public enum AuthorizationState {
    READY,
    UNDEFINED,
    WAIT_TDLIB_PARAMETERS,
    WAIT_PHONE_NUMBER,
    WAIT_PHONE_CODE
}
