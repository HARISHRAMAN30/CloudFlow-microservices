package com.cloudflow.common.response;

import com.cloudflow.common.exception.ErrorCode;

import java.time.Instant;

public record ApiErrorResponse(boolean success, String message, Instant timestamp, ApiErrorDetails error) {

    public static ApiErrorResponse of(ErrorCode errorCode) {
        return new ApiErrorResponse(false, errorCode.getDefaultMessage(), Instant.now(), new ApiErrorDetails(errorCode.getCode(), errorCode.getDefaultMessage()));
    }

    public static ApiErrorResponse of(ErrorCode errorCode, String message) {
        return new ApiErrorResponse(false, message, Instant.now(), new ApiErrorDetails(errorCode.getCode(), errorCode.getDefaultMessage()));
    }
}