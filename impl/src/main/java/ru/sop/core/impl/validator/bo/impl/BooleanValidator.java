package ru.sop.core.impl.validator.bo.impl;

import java.util.Objects;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Component;
import ru.bestclick.exceptionlib.validator.ValidationResult;
import ru.bestclick.exceptionlib.validator.impl.ValidationResultDefault;
import ru.sop.core.impl.enums.FieldTypeEnum;
import ru.sop.core.impl.validator.bo.BOFieldValidator;

@Component
public class BooleanValidator implements BOFieldValidator {

    @Override
    @Nullable
    public ValidationResult validate(Object bool) {
        if (bool instanceof Boolean) {
            return null;
        }
        var str = Objects.toString(bool);
        if (str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false")) {
            return null;
        }
        return ValidationResultDefault.builder()
            .message("field.type.boolean.not_valid")
            .logMessage("field.type.boolean.not_valid")
            .build();
    }

    @Override
    public String getType() {
        return FieldTypeEnum.BOOLEAN.getType();
    }
}
