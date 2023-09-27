package com.demo.common.rest.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

    public int code = HttpStatus.OK.value();

    private T data;

    private Metadata meta;

    public static <T> BaseResponse<T> ofSucceeded(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.data = data;
        return response;
    }

    public static <T> BaseResponse<T> ofSucceeded(T data, HttpStatus status) {
        BaseResponse<T> response = ofSucceeded(data);
        response.code = status.value();
        return response;
    }

    public static BaseResponse<Void> ofFailed(ErrorResponse error) {
        BaseResponse<Void> response = new BaseResponse<>();
        response.code = error.getHttpStatus().value();
        response.meta = new Metadata();
        response.meta.code = error.getCode();
        response.meta.message = error.getMessage();
        response.meta.details = error.getDetails();
        return response;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Metadata {

        String code;

        Integer page;

        Integer size;

        Long total;

        String message;

        Object details;

    }

}
