package com.demo.common.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ErrorResponse {

    public ErrorResponse(String code, String message, HttpStatus httpStatus) {
        super();
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
        this.details = null;
    }

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
    private final Object details;

}
