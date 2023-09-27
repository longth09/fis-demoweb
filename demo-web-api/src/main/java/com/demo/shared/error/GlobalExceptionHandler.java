package com.demo.shared.error;

import lombok.extern.slf4j.Slf4j;
import com.demo.common.exception.BusinessException;
import com.demo.common.exception.FieldError;
import com.demo.common.rest.response.BaseResponse;
import com.demo.common.rest.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static com.demo.common.exception.DefaultErrorCode.DEFAULT_BAD_REQUEST;
import static com.demo.common.exception.DefaultErrorCode.DEFAULT_INTERNAL_SERVER_ERROR;

/**
 * All exceptions will be handled in this class.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Handle field validation errors for reactive programming (webflux).
     *
     * @param ex the given exception.
     * @return An response entity with bad request status and including detail message.
     */
    @ExceptionHandler(value = {WebExchangeBindException.class,
            MethodArgumentNotValidException.class})
    public ResponseEntity<BaseResponse<Void>> handleWebExchangeBindException(
            WebExchangeBindException ex) {
        log.error("GlobalExceptionHandler:", ex);

        List<FieldError> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(FieldError::new)
                .collect(Collectors.toList());

        BaseResponse<Void> data = BaseResponse.ofFailed(ErrorResponse.builder()
                .code(DEFAULT_BAD_REQUEST.getCode())
                .details(errors)
                .httpStatus(DEFAULT_BAD_REQUEST.getHttpStatus())
                .message(DEFAULT_BAD_REQUEST.getMessage()).build());

        return new ResponseEntity<>(data, DEFAULT_BAD_REQUEST.getHttpStatus());
    }

    /**
     * Handle the all the rest exceptions.
     *
     * @param ex the given exception.
     * @return An response entity with internal server error status and including detail message.
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<BaseResponse<Void>> handleExceptions(Exception ex) {
        log.error("GlobalExceptionHandler:", ex);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(DEFAULT_INTERNAL_SERVER_ERROR.getCode())
                .httpStatus(DEFAULT_INTERNAL_SERVER_ERROR.getHttpStatus())
                .message(DEFAULT_INTERNAL_SERVER_ERROR.getMessage()).build();

        BaseResponse<Void> data = BaseResponse.ofFailed(errorResponse);
        return new ResponseEntity<>(data, DEFAULT_INTERNAL_SERVER_ERROR.getHttpStatus());

    }

    /**
     * Handle business logic validation errors
     *
     * @param ex the given exception.
     * @return An response entity with bad request status and including detail message.
     */
    @ExceptionHandler(value = ResponseStatusException.class)
    public ResponseEntity<BaseResponse<Void>> handleServerWebInputException(
            ResponseStatusException ex) {

        BaseResponse<Void> data = BaseResponse.ofFailed(ErrorResponse.builder()
                .code(DEFAULT_BAD_REQUEST.getCode())
                .httpStatus(DEFAULT_BAD_REQUEST.getHttpStatus())
                .message(DEFAULT_BAD_REQUEST.getMessage()).build());

        return new ResponseEntity<>(data, DEFAULT_BAD_REQUEST.getHttpStatus());
    }

    /**
     * Handle business logic validation errors
     *
     * @param ex the given exception.
     * @return An response entity with bad request status and including detail message.
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<BaseResponse<Void>> handleBusinessException(BusinessException ex) {
        log.error("GlobalExceptionHandler:", ex);
        String message = ex.getDetailError() == null ? ex.getMessage()
                : ex.getDetailError().toString();

        BaseResponse<Void> data = BaseResponse.ofFailed(ErrorResponse.builder()
                .code(ex.getCode())
                .message(message)
                .httpStatus(ex.getStatus()).build());
        return new ResponseEntity<>(data, ex.getStatus());
    }
}
