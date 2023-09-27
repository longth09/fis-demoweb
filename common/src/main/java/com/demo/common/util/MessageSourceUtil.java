package com.demo.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MessageSourceUtil {
    private final MessageSource messageSource;
    private static MessageSourceUtil instance;

    private Locale locale;

    public String getMessage(String key) {
        return getMessage(key, null, key);
    }

    public String getMessage(String code, String defaultMessage) {
        return getMessage(code, null, defaultMessage);
    }

    public String getMessage(String key, Object[] args, String defaultMessage) {
        try {
            if (defaultMessage == null) {
                return messageSource.getMessage(key, args, locale);
            }
            return messageSource.getMessage(key, args, defaultMessage, locale);
        } catch (Exception e) {
            return key;
        }
    }

    public String getFieldErrorMessage(FieldError error, Object... argArr) {
        String message = error.getDefaultMessage();
        String key =
                isMessageKey(message) ? extractMessageKeyFromTemplate(message) : error.getCode();
        List<String> args = buildArguments(argArr);
        args.add(0, getField(error));
        return getMessage(key, args.toArray(), message);
    }

    public static MessageSourceUtil getInstance() {
        return instance;
    }

    @PostConstruct
    private void setInstance() {
        instance = this;
    }

    private List<String> buildArguments(Object[] argArr) {
        return Arrays.stream(argArr)
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    private boolean isMessageKey(String input) {
        if (input == null || input.isBlank()) {
            return false;
        }
        return input.startsWith("{") && input.endsWith("}");
    }

    private String extractMessageKeyFromTemplate(String template) {
        return template.substring(1, template.length() - 1);
    }

    private String getField(FieldError error) {
        String field = error.getField();
        String fullFieldName = error.getObjectName() + "." + field;
        String message = getMessage(fullFieldName);
        if (message.equals(fullFieldName)) {
            if (!field.contains(".")) {
                return getMessage(field);
            }
            String[] elements = field.split("\\.");
            return getMessage(elements[elements.length - 1]);
        }
        return message;
    }

}
