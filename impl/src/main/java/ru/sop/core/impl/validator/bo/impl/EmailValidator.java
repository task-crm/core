package ru.sop.core.impl.validator.bo.impl;

import java.util.Objects;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Component;
import ru.bestclick.exceptionlib.validator.ValidationResult;
import ru.bestclick.exceptionlib.validator.impl.ValidationResultDefault;
import ru.sop.core.impl.enums.FieldTypeEnum;
import ru.sop.core.impl.validator.bo.BOFieldValidator;

@Component
public class EmailValidator implements BOFieldValidator {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    @Override
    @Nullable
    public ValidationResult validate(Object email) {
        if (Pattern.compile(EMAIL_PATTERN)
            .matcher(Objects.toString(email))
            .matches()) {
            return null;
        }
        return ValidationResultDefault.builder()
            .message("field.type.email.not_valid")
            .logMessage("field.type.email.not_valid")
            .build();
    }

    @Override
    public String getType() {
        return FieldTypeEnum.EMAIL.getType();
    }
}
