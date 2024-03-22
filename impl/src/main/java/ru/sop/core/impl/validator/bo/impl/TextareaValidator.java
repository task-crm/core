package ru.sop.core.impl.validator.bo.impl;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Component;
import ru.bestclick.exceptionlib.validator.ValidationResult;
import ru.bestclick.exceptionlib.validator.impl.ValidationResultDefault;
import ru.sop.core.impl.enums.FieldTypeEnum;
import ru.sop.core.impl.validator.bo.BOFieldValidator;

@Component
public class TextareaValidator implements BOFieldValidator {
    private static final int MAX_TEXTAREA_LENGTH = 10000;

    @Override
    @Nullable
    public ValidationResult validate(Object textarea) {
        if (textarea instanceof String str && str.length() < MAX_TEXTAREA_LENGTH) {
            return null;
        }
        return ValidationResultDefault.builder()
            .message("field.type.textarea.not_valid")
            .logMessage("field.type.textarea.not_valid")
            .build();
    }

    @Override
    public String getType() {
        return FieldTypeEnum.TEXTAREA.getType();
    }
}
