package com.cloudflow.web.exception;

import com.cloudflow.common.exception.CloudFlowException;
import com.cloudflow.common.exception.ErrorCode;
import com.cloudflow.common.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CloudFlowException.class)
    public ResponseEntity<ApiErrorResponse> handleCloudFlowException(CloudFlowException ex) {
        ErrorCode error = ex.getErrorCode();
        ApiErrorResponse response = ApiErrorResponse.of(error, ex.getMessage());

        return ResponseEntity
                .status(error.getHttpStatus())
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException ex) {

        String message =
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .findFirst()
                        .map(FieldError::getDefaultMessage)
                        .orElse("Validation failed");

        ApiErrorResponse response = ApiErrorResponse.of(ErrorCode.VALIDATION_FAILED, message);

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception ex) {

        ApiErrorResponse response = ApiErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
