package com.demo.common.exception;

import com.demo.common.rest.response.ErrorResponse;
import org.springframework.http.HttpStatus;

/**
 * Default error code will be defined here.
 */
public final class DefaultErrorCode {

    private DefaultErrorCode() {
        throw new IllegalStateException("Utility class");
    }

    public static final ErrorResponse DEFAULT_INTERNAL_SERVER_ERROR = new ErrorResponse("000500",
            "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    public static final ErrorResponse DEFAULT_SERVICE_UNAVAILABLE = new ErrorResponse("000503",
            "Service Unavailable", HttpStatus.SERVICE_UNAVAILABLE);
    public static final ErrorResponse DEFAULT_BAD_REQUEST = new ErrorResponse("000400",
            "Bad Request", HttpStatus.BAD_REQUEST);
    public static final ErrorResponse DEFAULT_FORBIDDEN = new ErrorResponse("000403",
            "Forbidden", HttpStatus.FORBIDDEN);
    public static final ErrorResponse DEFAULT_NOT_FOUND = new ErrorResponse("000404",
            "Item not found", HttpStatus.NOT_FOUND);

    public static final ErrorResponse AUTHENTICATION_REQUIRED = new ErrorResponse("000510",
            "This request is required authentication", HttpStatus.UNAUTHORIZED);
    public static final ErrorResponse AUTHENTICATION_FAILED = new ErrorResponse("000511",
            "Authentication failed", HttpStatus.UNAUTHORIZED);
    public static final ErrorResponse TOKEN_INVALID = new ErrorResponse("000512",
            "Token Invalid", HttpStatus.UNAUTHORIZED);
    public static final ErrorResponse MAIL_DATA_INVALID = new ErrorResponse("000513",
            "Request mail data invalid", HttpStatus.BAD_REQUEST);
    public static final ErrorResponse API_SEND_MAIL_FAILED = new ErrorResponse("000514",
            "Error on call api send mail", HttpStatus.INTERNAL_SERVER_ERROR);

    public static final ErrorResponse COUPON_INVALID = new ErrorResponse("000515",
            "Coupon invalid", HttpStatus.BAD_REQUEST);

    public static final ErrorResponse EVENT_INVALID = new ErrorResponse("000515",
            "Event invalid", HttpStatus.BAD_REQUEST);

    public static final ErrorResponse ENTITY_DUPLICATE_ERROR = new ErrorResponse("000516",
            "Error duplicate entity", HttpStatus.INTERNAL_SERVER_ERROR);

    public static final ErrorResponse EVENT_DUPLICATE_SLUG_ERROR = new ErrorResponse("000516",
            "Event duplicate slug", HttpStatus.INTERNAL_SERVER_ERROR);

    public static final ErrorResponse PAYMENT_INVALID = new ErrorResponse("000517",
            "Payment invalid", HttpStatus.BAD_REQUEST);

    public static final ErrorResponse ACCOUNT_NOT_FOUND = new ErrorResponse("000519",
            "Account not found", HttpStatus.NOT_FOUND);

    public static final ErrorResponse HASH_INVALID_OR_EXPIRED = new ErrorResponse("000520",
            "Hash invalid or expired", HttpStatus.BAD_REQUEST);

    public static final ErrorResponse COUPON_QUANTITY_MUST_GREATER_THAN_ZERO = new ErrorResponse("000521",
            "coupon quantity must greater than zero", HttpStatus.BAD_REQUEST);

    public static final ErrorResponse EVENT_NOT_FOUND = new ErrorResponse("000522",
            "Event not found", HttpStatus.NOT_FOUND);
    public static final ErrorResponse FOOD_NOT_FOUND = new ErrorResponse("000523",
            "Food not found", HttpStatus.NOT_FOUND);
}
