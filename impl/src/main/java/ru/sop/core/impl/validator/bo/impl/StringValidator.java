package ru.sop.core.impl.validator.bo.impl;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Component;
import ru.bestclick.exceptionlib.validator.ValidationResult;
import ru.bestclick.exceptionlib.validator.impl.ValidationResultDefault;
import ru.sop.core.impl.enums.FieldTypeEnum;
import ru.sop.core.impl.validator.bo.BOFieldValidator;

@Component
public class StringValidator implements BOFieldValidator {
    private static final int MAX_STRING_LENGTH = 1000;

    @Override
    @Nullable
    public ValidationResult validate(Object str) {
        if (str instanceof String text && text.length() < MAX_STRING_LENGTH) {
            return null;
        }
        return ValidationResultDefault.builder()
            .message("field.type.string.not_valid")
            .logMessage("field.type.string.not_valid")
            .build();
    }

    @Override
    public String getType() {
        return FieldTypeEnum.STRING.getType();
    }
}
