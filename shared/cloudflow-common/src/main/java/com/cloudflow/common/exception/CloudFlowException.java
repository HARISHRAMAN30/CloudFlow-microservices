package com.cloudflow.common.exception;

public class CloudFlowException extends RuntimeException {

    private final ErrorCode errorCode;

    public CloudFlowException(ErrorCode errorCode) {
        super(errorCode.getDefaultMessage());
        this.errorCode = errorCode;
    }

    public CloudFlowException(ErrorCode errorCode, String customMessage) {
        super(customMessage);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
    public CloudFlowException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
