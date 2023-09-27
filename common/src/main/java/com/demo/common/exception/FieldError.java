package com.demo.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.demo.common.util.CaseUtil;
import com.demo.common.util.MessageSourceUtil;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Contains detail of field errors.
 */
@Getter
@RequiredArgsConstructor
public class FieldError implements Serializable {

    private static final long serialVersionUID = -576386799095858262L;

    private final String field;

    private final String message;

    private final String code;

    private transient Object[] args;

    /**
     * Create an instance from a given {@link ObjectError}.
     *
     * @param error the given instance of {@link ObjectError}.
     */
    public FieldError(ObjectError error) {
        org.springframework.validation.FieldError fieldError = ((org.springframework.validation.FieldError) error);
        this.code = fieldError.getCode();
        this.field = CaseUtil.toSnakeCase(fieldError.getField());
        this.args = fieldError.getArguments() != null && fieldError.getArguments().length > 1
                ? Arrays.copyOfRange(fieldError.getArguments(), 1, fieldError.getArguments().length)
                : new Object[0];
        this.message = MessageSourceUtil.getInstance() != null ? MessageSourceUtil
                .getInstance().getFieldErrorMessage(fieldError, this.args)
                : error.getDefaultMessage();

    }

    /**
     * Create an instance from a given {@link ConstraintViolation}.
     *
     * @param violation the given instance of {@link ObjectError}.
     */
    public FieldError(ConstraintViolation<?> violation) {
        this.code = null;
        this.field = CaseUtil.toSnakeCase(violation.getPropertyPath().toString());
        this.message = violation.getMessage();
    }

    @Override
    public String toString() {
        return message;
    }
}
