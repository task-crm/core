package ru.sop.core.impl.validator.bo.impl;

import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Component;
import ru.bestclick.exceptionlib.validator.ValidationResult;
import ru.bestclick.exceptionlib.validator.impl.ValidationResultDefault;
import ru.sop.core.impl.enums.FieldTypeEnum;
import ru.sop.core.impl.validator.bo.BOFieldValidator;

@Component
public class NumberValidator implements BOFieldValidator {

    @Override
    @Nullable
    public ValidationResult validate(Object number) {
        if (number instanceof Number) {
            return null;
        }
        if (StringUtils.isNumeric(Objects.toString(number))) {
            return null;
        }
        return ValidationResultDefault.builder()
            .message("field.type.number.not_valid")
            .logMessage("field.type.number.not_valid")
            .build();
    }

    @Override
    public String getType() {
        return FieldTypeEnum.NUMBER.getType();
    }
}
