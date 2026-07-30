package com.cloudflow.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // ==========================
    // Generic
    // ==========================

    INTERNAL_SERVER_ERROR(500, "CF-5000", "An unexpected error occurred"),

    VALIDATION_FAILED(400, "CF-4000", "Validation failed"),

    RESOURCE_NOT_FOUND(404, "CF-4040", "Resource not found"),

    // ==========================
    // Authentication
    // ==========================

    INVALID_CREDENTIALS(401, "AUTH-4013", "Invalid username or password"),

    INVALID_TOKEN(401, "AUTH-4011", "Invalid JWT token"),

    UNAUTHORIZED(401, "AUTH-4010", "Authentication required"),

    TOKEN_EXPIRED(401, "AUTH-4012", "JWT token expired"),

    ACCESS_DENIED(403, "AUTH-4030", "Access denied"),

    // ==========================
    // Tenant
    // ==========================

    TENANT_NOT_FOUND(404, "TENANT-4040", "Tenant not found"),

    TENANT_DISABLED(403, "TENANT-4030", "Tenant is disabled"),

    // ==========================
    // Upload
    // ==========================

    INVALID_FILE_TYPE(400, "UPLOAD-4001", "Unsupported file type"),

    FILE_TOO_LARGE(413, "UPLOAD-4130", "File exceeds allowed size"),

    PRESIGNED_URL_GENERATION_FAILED(500, "UPLOAD-5001", "Unable to generate upload URL"),

    // ==========================
    // Job
    // ==========================

    JOB_NOT_FOUND(404, "JOB-4040", "Job not found"),

    JOB_ALREADY_COMPLETED(409, "JOB-4090", "Job already completed"),

    JOB_ALREADY_RUNNING(409, "JOB-4091", "Job already running");

    private final int httpStatus;
    private final String code;
    private final String defaultMessage;

    ErrorCode(int httpStatus, String code, String defaultMessage) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.defaultMessage = defaultMessage;
    }
}