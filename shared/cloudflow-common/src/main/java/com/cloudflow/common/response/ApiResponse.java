package com.cloudflow.common.response;

import java.time.Instant;

public record ApiResponse<T>(boolean success, String message,Instant timestamp, T data) {

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "Success", Instant.now(), data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, Instant.now(), data);
    }
}