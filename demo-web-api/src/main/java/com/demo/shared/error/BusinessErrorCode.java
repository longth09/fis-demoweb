package com.demo.shared.error;

import com.demo.common.rest.response.ErrorResponse;
import org.springframework.http.HttpStatus;

/**
 * All business error code will be defined here.
 */
public final class BusinessErrorCode {

    public static final ErrorResponse EMAIL_OR_PASSWORD_INVALID = new ErrorResponse("000400",
            "Email or password invalid", HttpStatus.BAD_REQUEST);
    public static final ErrorResponse EMAIL_DUPLICATE = new ErrorResponse("000400",
            "Email address already in use.", HttpStatus.BAD_REQUEST);
    public static final ErrorResponse USER_INVALID = new ErrorResponse("000400",
            "User not found..", HttpStatus.BAD_REQUEST);

    private BusinessErrorCode() {
        throw new IllegalStateException("Utility class");
    }


}
