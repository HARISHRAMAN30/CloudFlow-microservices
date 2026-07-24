package com.cloudflow.common.response;

import java.time.Instant;

public record ApiErrorResponse(boolean success, String message, Instant timestamp, ApiErrorDetails error) {

    public static ApiErrorResponse of(String message, ApiErrorDetails error) {
        return new ApiErrorResponse(false, message, Instant.now(), error);
    }
}