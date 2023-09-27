package com.demo.common.exception;

import com.demo.common.rest.response.ErrorResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1138067521792343348L;
    private final String code;
    private final String message;
    private final HttpStatus status;
    private final Object detailError;
    private final Throwable rootCause;

    public BusinessException(ErrorResponse errorResponse) {
        this(errorResponse.getCode(), errorResponse.getMessage(), errorResponse.getHttpStatus());
    }

    public BusinessException(String errorCode, String message, HttpStatus status) {
        this(errorCode, message, status, null, null);
    }

    public BusinessException(String errorCode, String message, HttpStatus status, Object detail) {
        this(errorCode, message, status, detail, null);
    }

    public BusinessException(String errorCode, String message, HttpStatus status, Object detail,
                             Throwable cause) {
        this.code = errorCode;
        this.message = message;
        this.status = status;
        this.detailError = detail;
        this.rootCause = cause;
    }
}
